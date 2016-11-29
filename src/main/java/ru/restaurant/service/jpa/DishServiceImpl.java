package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.DishRepository;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.DishService;
import ru.restaurant.to.RestaurantDishes;
import ru.restaurant.util.DishUtil;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.*;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            return dishRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete dish");
        }
    }

    @Override
    public Map<Restaurant, Set<Dish>> getAllOnDate(LocalDate date) {
        Collection<Dish> result = dishRepository.getAll(date);
        if (!result.isEmpty()) {
            return DishUtil.dishesWithRestaurants(result);
        } else return Collections.emptyMap();

    }

    @Override
    public Dish update(Dish dish, int userId) throws NotFoundException {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            dish.setDate(LocalDate.now());
            return dishRepository.save(dish);
        } else {
            throw new AccessDeniedException("You can't update dish");
        }
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId, int userId) {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            setRestaurant(dish, restaurantId);
            dish.setDate(LocalDate.now());
            return dishRepository.save(dish);
        } else {
            throw new AccessDeniedException("You can't save dish");
        }
    }

    @Override
    public Dish get(int dishId) {
        Dish result = dishRepository.get(dishId);
        Assert.notNull(result, "can't find request dish");
        return result;
    }

    private void setRestaurant(Dish dish, Integer restaurantId){
//        DishUtil.checkId(dish);
        Restaurant restaurant = restaurantRepository.get(restaurantId);
//        Restaurant restaurant = restaurantService.get(dish.getRestaurant().getId());
        if (restaurant != null){
            dish.setRestaurant(restaurant);
        } else {
            throw new NotFoundException("Not found Restaurant");
        }
    }
}
