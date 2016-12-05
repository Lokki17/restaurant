package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.DishRepository;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.service.DishService;
import ru.restaurant.util.DishUtil;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.*;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

/*    @Autowired
    UserRepository userRepository;*/

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean delete(int id) {
        return dishRepository.delete(id);
    }

    @Override
    public Map<Restaurant, Set<Dish>> getAllOnDate(LocalDate date) {
        Collection<Dish> result = dishRepository.getAll(date);
        if (!result.isEmpty()) {
            return DishUtil.dishesWithRestaurants(result);
        } else return Collections.emptyMap();

    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
//        User savedUser = userRepository.checkUser(userId);
        setRestaurant(dish, restaurantId);
        dish.setDate(LocalDate.now());
        return checkDish(dishRepository.save(dish), "Dish didn't update");
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        setRestaurant(dish, restaurantId);
        dish.setDate(LocalDate.now());
        return checkDish(dishRepository.save(dish), "Dish didn't save");
    }

    @Override
    public Dish get(int dishId) {
        return checkDish(dishRepository.get(dishId), "Can't find request dish");
    }

    private void setRestaurant(Dish dish, Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        Assert.notNull(restaurant, "Not found Restaurant");
        dish.setRestaurant(restaurant);
    }

    private Dish checkDish(Dish result, String message){
        if (result == null){
            throw new NotFoundException(message);
        }
        return result;
    }
}
