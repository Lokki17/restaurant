package ru.restaurant.repository;

import ru.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Restaurant get(int id);

    Restaurant get(String restaurantName);

    // ORDERED dateTime
    List<Restaurant> getAll();

    Restaurant checkUser(Integer restaurantId);

    // ORDERED dateTime
    //Collection<Restaurant> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
