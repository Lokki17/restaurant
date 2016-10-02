package ru.restuarant.service;

import ru.restuarant.model.Dish;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface DishService {
    //Dish get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Dish> getAllOnDete(LocalDateTime dateTime);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
}
