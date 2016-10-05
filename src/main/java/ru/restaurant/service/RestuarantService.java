package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

public interface RestuarantService {

    boolean delete(int id, int userId) throws NotFoundException;

    Collection<Restaurant> getAll();

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    Restaurant save(Restaurant restaurant, int userId);
}
