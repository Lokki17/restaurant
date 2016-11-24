package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

public interface RestaurantService {

    boolean delete(int id, int userId) throws NotFoundException;

    Collection<Restaurant> getAll();

    Restaurant get(Integer restaurantId);

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    Restaurant save(Restaurant restaurant, int userId);
}
