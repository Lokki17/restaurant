package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null){
            return null;
        }
        if (restaurant.isNew()) {
            em.persist(restaurant);
        } else {
            em.merge(restaurant);
        }
        return restaurant;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE).setParameter("restuarantId", id).executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }

    @Override
    public Restaurant checkUser(Integer restaurantId) {
        Restaurant savedRestaurant = get(restaurantId);
        Assert.notNull(savedRestaurant, "can't find restaurant");
        return savedRestaurant;
    }
}
