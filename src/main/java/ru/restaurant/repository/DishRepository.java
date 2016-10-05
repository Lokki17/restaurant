package ru.restaurant.repository;

import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface DishRepository {
    // null if updated meal do not belong to userId
    Dish save(Dish dish, int userId, LocalDate localDate) throws WrongTimeException;

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    //Dish get(int id, int userId);

    // ORDERED dateTime
    Collection<Dish> getAll(LocalDate localDate);

    // ORDERED dateTime
    //Collection<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
