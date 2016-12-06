package ru.restaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import ru.restaurant.model.Dish;
import ru.restaurant.util.exception.WrongTimeException;

import javax.persistence.NamedEntityGraph;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface DishRepository {

    Dish save(Dish dish) throws WrongTimeException;

    boolean delete(int id);

    Dish get(int id);

    Collection<Dish> getAll(LocalDate localDate);

}
