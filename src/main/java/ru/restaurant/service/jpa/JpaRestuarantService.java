package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestuarantRepository;
import ru.restaurant.service.RestuarantService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class JpaRestuarantService implements RestuarantService{

    @Autowired
    RestuarantRepository restuarantRepository;

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<Restaurant> getAll() {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return null;
    }
}
