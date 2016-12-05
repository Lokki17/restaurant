package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

public interface RestaurantService {

    boolean delete(int id) throws NotFoundException;

    Collection<Restaurant> getAll();

    Restaurant get(Integer restaurantId);

    Restaurant getByName(String restaurantName);

    Restaurant update(Restaurant restaurant);

    Restaurant save(Restaurant restaurant);
}
