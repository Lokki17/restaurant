package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Dish;
import ru.restaurant.repository.DishRepository;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.exception.WrongTimeException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Repository
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Dish save(Dish dish, int userId, LocalDate localDate) {
        Dish savedDish = em.createNamedQuery(Dish.GET, Dish.class)
                .setParameter("dishId", dish.getId())
                .setParameter("dateTime", localDate)
                .getSingleResult();
        if (savedDish == null) {
            em.persist(dish);
        } else {
            em.merge(dish);
        }
        return dish;
    }

    @Override
    public Collection<Dish> getAll(LocalDate localDate) {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).setParameter("dateTime", localDate).getResultList();
    }
}
