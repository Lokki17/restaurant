package ru.restuarant.service;

import ru.restuarant.model.Dish;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface DishService {
    //Dish get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    default Collection<Dish> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    Collection<Dish> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<Dish> getAll(LocalDateTime dateTime);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
}
