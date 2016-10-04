package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Dish;
import ru.restaurant.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Dish save(Dish dish, int userId) {
        return null;
    }

    @Override
    public Collection<Dish> getAll(LocalDateTime dateTime) {
        return null;
    }
}
