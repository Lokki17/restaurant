package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        return restaurantRepository.delete(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        Collection<Restaurant> result = restaurantRepository.getAll();
        if (!result.isEmpty()) {
            return result;
        } else return Collections.emptyList();
    }

    @Override
    public Restaurant get(Integer restaurantId) {
        Restaurant result = restaurantRepository.get(restaurantId);
        Assert.notNull(result, "can't find request restaurant");
        return result;
    }

    @Override
    public Restaurant getByName(String restaurantName) {
        Restaurant result = restaurantRepository.get(restaurantName);
        Assert.notNull(result, "can't find request restaurant");
        return result;
    }

    @Override
    public Restaurant update(Restaurant restaurant, int userId) throws NotFoundException {
        Restaurant result = restaurantRepository.save(restaurant);
        Assert.notNull(result, "can't find request restaurant");
        return result;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        Restaurant result =  restaurantRepository.save(restaurant);
        Assert.notNull(result, "can't find request restaurant");
        return result;
    }
}
