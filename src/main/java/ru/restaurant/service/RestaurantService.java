package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface RestaurantService {

    boolean delete(int id);

    List<Restaurant> getAll();

    Restaurant get(Integer restaurantId);

    Restaurant getByName(String restaurantName);

    Restaurant update(Restaurant restaurant);

    Restaurant save(Restaurant restaurant);
}
