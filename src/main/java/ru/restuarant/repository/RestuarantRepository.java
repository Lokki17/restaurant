package ru.restuarant.repository;

import ru.restuarant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Collection;

public interface RestuarantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    //Restaurant get(int id, int userId);

    // ORDERED dateTime
    Collection<Restaurant> getAll(LocalDateTime dateTime);

    // ORDERED dateTime
    //Collection<Restaurant> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
