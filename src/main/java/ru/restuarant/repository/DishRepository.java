package ru.restuarant.repository;

import ru.restuarant.model.Dish;

import java.time.LocalDateTime;
import java.util.Collection;

public interface DishRepository {
    // null if updated meal do not belong to userId
    Dish save(Dish dish, int userId);

    // false if meal do not belong to userId
    //boolean delete(int id, int userId);

    // null if meal do not belong to userId
    //Dish get(int id, int userId);

    // ORDERED dateTime
    Collection<Dish> getAll(LocalDateTime dateTime);

    // ORDERED dateTime
    //Collection<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
