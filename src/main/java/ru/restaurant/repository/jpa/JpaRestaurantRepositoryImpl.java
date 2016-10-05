package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurant savedRestaurant = em.createNamedQuery(Restaurant.GET, Restaurant.class)
                .setParameter("restaurantId", restaurant.getId())
                .getSingleResult();
        if (savedRestaurant == null) {
            em.persist(restaurant);
        } else {
            restaurant.setId(savedRestaurant.getId());
            em.merge(restaurant);
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE, Restaurant.class).setParameter("restuarantId", id).executeUpdate() != 0;
    }

    @Override
    public Collection<Restaurant> getAll(LocalDateTime dateTime) {
        return em.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }
}
