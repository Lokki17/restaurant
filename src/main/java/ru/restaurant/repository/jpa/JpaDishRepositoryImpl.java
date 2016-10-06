package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Dish;
import ru.restaurant.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
        } else {
            em.merge(dish);
        }
        return dish;
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE, Dish.class).setParameter("dishId", id).executeUpdate() != 0;
    }

    @Override
    public Collection<Dish> getAll(LocalDate localDate) {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).setParameter("dateTime", localDate).getResultList();
    }
}
