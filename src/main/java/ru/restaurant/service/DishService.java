package ru.restaurant.service;

import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.to.RestaurantDishes;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface DishService {
    //Dish get(int id, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    Map<Restaurant, Set<Dish>> getAllOnDate(LocalDate dateTime);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
//    Dish save(Dish dish, int restaurantId, int userId);

    Dish get(int userId);
}
