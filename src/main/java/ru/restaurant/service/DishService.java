package ru.restaurant.service;

import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface DishService {
    //Dish get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Dish> getAllOnDete(LocalDateTime dateTime);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
}
