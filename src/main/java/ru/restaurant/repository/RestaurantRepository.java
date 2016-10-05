package ru.restaurant.repository;

import ru.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Collection;

public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    //Restaurant get(int id, int userId);

    // ORDERED dateTime
    Collection<Restaurant> getAll();

    // ORDERED dateTime
    //Collection<Restaurant> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
