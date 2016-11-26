package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.model.Dish;
import ru.restaurant.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if (!dish.isNew() && get(dish.getId()) == null){
            return null;
        }
        if (dish.isNew()) {
            em.persist(dish);
        } else {
            em.merge(dish);
        }
        return dish;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE).setParameter("dishId", id).executeUpdate() != 0;
    }

    @Override
    public Dish get(int dishId) {
        return em.find(Dish.class, dishId);
    }

    @Override
    public Collection<Dish> getAll(LocalDate localDate) {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).setParameter("date", localDate).getResultList();
    }
}
