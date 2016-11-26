package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Objects;

@Service
public class JpaRestaurantService implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            return restaurantRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete restaurant");
        }
    }

    @Override
    public Collection<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public Restaurant get(Integer restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            restaurantRepository.save(restaurant);
        } else {
            throw new AccessDeniedException("You can't update restaurant");
        }
        return restaurant;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            restaurantRepository.save(restaurant);
        } else {
            throw new AccessDeniedException("You can't save restaurant");
        }
        return restaurant;
    }
}
