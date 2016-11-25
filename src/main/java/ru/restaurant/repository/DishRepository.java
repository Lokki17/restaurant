package ru.restaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.WrongTimeException;

import javax.persistence.NamedEntityGraph;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface DishRepository {
    // null if updated meal do not belong to userId
    Dish save(Dish dish) throws WrongTimeException;

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Dish get(int id);

    // ORDERED dateTime
//    @EntityGraph(value = Dish.GRAPH)
    Collection<Dish> getAll(LocalDate localDate);

    // ORDERED dateTime
    //Collection<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
