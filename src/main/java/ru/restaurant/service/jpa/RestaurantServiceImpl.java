package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.EntityUtil;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.restaurant.util.EntityUtil.checkForNull;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean delete(int id) throws NotFoundException {
        Boolean result = restaurantRepository.delete(id);
        if (result){
            return true;
        } else throw new NotFoundException("Can't find request restaurant");
    }

    @Override
    public List<Restaurant> getAll() {
        List<Restaurant> result = restaurantRepository.getAll();
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
    public Restaurant update(Restaurant restaurant) {
        return checkRestaurant(restaurantRepository.save(restaurant), "Can't find request restaurant");
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return checkRestaurant(restaurantRepository.save(restaurant), "Restaurant didn't save");
    }

    private Restaurant checkRestaurant(Restaurant result, String message){
        checkForNull(result, message);
        return result;
    }
}
