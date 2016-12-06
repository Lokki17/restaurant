package ru.restaurant.repository;

import ru.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant get(String restaurantName);

    List<Restaurant> getAll();
}
