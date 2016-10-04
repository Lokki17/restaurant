package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Dish;
import ru.restaurant.repository.DishRepository;
import ru.restaurant.service.DishService;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JpaDishService implements DishService{

    @Autowired
    DishRepository dishRepository;

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<Dish> getAllOnDete(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public Dish update(Dish dish, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public Dish save(Dish dish, int userId) {
        return null;
    }
}
