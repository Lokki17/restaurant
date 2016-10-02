package ru.restuarant.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.restuarant.model.Dish;
import ru.restuarant.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JdbcDishRepositoryImpl implements DishRepository {
    @Override
    public Dish save(Dish dish, int userId) {
        return null;
    }

    @Override
    public Collection<Dish> getAll(LocalDateTime dateTime) {
        return null;
    }
}
