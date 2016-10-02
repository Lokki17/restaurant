package ru.restuarant.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restuarant.model.Dish;
import ru.restuarant.repository.DishRepository;
import ru.restuarant.service.DishService;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JdbcDishService implements DishService{

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
