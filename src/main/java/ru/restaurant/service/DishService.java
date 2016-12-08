package ru.restaurant.service;

import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface DishService {

    boolean delete(int id);

    Map<Restaurant, Set<Dish>> getAllOnDate(LocalDate dateTime);

    Dish update(Dish dish, int restaurantId);

    Dish save(Dish dish, int restaurantId);

    Dish get(int distId);
}
