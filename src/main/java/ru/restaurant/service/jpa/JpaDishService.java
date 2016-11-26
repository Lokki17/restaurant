package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class JpaDishService implements DishService{

    @Autowired
    DishRepository dishRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            return dishRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete dish");
        }
    }

    @Override
    public Map<Restaurant, Set<Dish>> getAllOnDate(LocalDate date) {
        return DishUtil.dishesWithRestaurants(dishRepository.getAll(date));

    }

    @Override
    public Dish update(Dish dish, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            dish.setDate(LocalDate.now());
            dishRepository.save(dish);
        } else {
            throw new AccessDeniedException("You can't update dish");
        }
        return dish;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int userId) {
//    public Dish save(Dish dish, int restaurantId, int userId) {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);

        if (savedUser.getRoles().contains(Role.ADMIN)){
            Restaurant savesRestaurant = restaurantRepository.get(dish.getRestaurant().getId());
            Objects.isNull(savesRestaurant);
            dish.setRestaurant(savesRestaurant);
            dish.setDate(LocalDate.now());
            dishRepository.save(dish);
        } else {
            throw new AccessDeniedException("You can't save dish");
        }
        return dish;
    }

    @Override
    public Dish get(int dishId) {
        return dishRepository.get(dishId);
    }
}
