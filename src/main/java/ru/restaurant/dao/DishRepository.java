package ru.restaurant.dao;

import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.util.Collection;

public interface DishRepository {

    Dish save(Dish dish) throws WrongTimeException;

    boolean delete(int id);

    Dish get(int id);

    Collection<Dish> getAll(LocalDate localDate);

}
