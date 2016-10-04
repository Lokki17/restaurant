package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestuarantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaRestuarantRepositoryImpl implements RestuarantRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Collection<Restaurant> getAll(LocalDateTime dateTime) {
        return null;
    }
}
