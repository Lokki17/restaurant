package ru.restuarant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restuarant.model.Restaurant;
import ru.restuarant.repository.RestuarantRepository;
import ru.restuarant.service.RestuarantService;
import ru.restuarant.util.exception.NotFoundException;

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
