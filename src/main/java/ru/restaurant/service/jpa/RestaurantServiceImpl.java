package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Collections;

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
        return checkRestaurant(restaurantRepository.get(restaurantId), "Can't find request restaurant");
    }

    @Override
    public Restaurant getByName(String restaurantName) {
        return checkRestaurant(restaurantRepository.get(restaurantName), "Can't find " + restaurantName + " restaurant");
    }

    @Override
    public Restaurant update(Restaurant restaurant, int userId) {
        return checkRestaurant(restaurantRepository.save(restaurant), "Can't find request restaurant");
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return checkRestaurant(restaurantRepository.save(restaurant), "Restaurant didn't save");
    }

    private Restaurant checkRestaurant(Restaurant result, String message){
        Assert.notNull(result, message);
        return result;
    }
}
