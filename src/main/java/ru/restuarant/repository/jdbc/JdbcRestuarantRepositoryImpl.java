package ru.restuarant.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.restuarant.model.Restaurant;
import ru.restuarant.repository.RestuarantRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JdbcRestuarantRepositoryImpl implements RestuarantRepository{
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
