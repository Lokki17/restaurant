package ru.restuarant.service;

import ru.restuarant.model.Restaurant;
import ru.restuarant.util.exception.NotFoundException;

import java.util.Collection;

public interface RestuarantService {

    void delete(int id, int userId) throws NotFoundException;

    Collection<Restaurant> getAll();

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    Restaurant save(Restaurant restaurant, int userId);
}
