package ru.restaurant.service;

import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface DishService {
    //Dish get(int id, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    Collection<Dish> getAllOnDate(LocalDate dateTime);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
}
