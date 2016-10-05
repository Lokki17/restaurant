package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Dish;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.DishRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.DishService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JpaDishService implements DishService{

    @Autowired
    DishRepository dishRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User user = userRepository.get(userId);
        if (user.getRole().contains(Role.ADMIN)){
            return dishRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete dish");
        }
    }

    @Override
    public Collection<Dish> getAllOnDete(LocalDate dateTime) {
        return dishRepository.getAll(dateTime);
    }

    @Override
    public Dish update(Dish dish, int userId) throws NotFoundException {
        User user = userRepository.get(userId);
        if (user.getRole().contains(Role.ADMIN)){
            dishRepository.save(dish, LocalDate.now());
        } else {
            throw new AccessDeniedException("You can't update dish");
        }
        return dish;
    }

    @Override
    public Dish save(Dish dish, int userId) {
        User user = userRepository.get(userId);
        if (user.getRole().contains(Role.ADMIN)){
            dishRepository.save(dish, LocalDate.now());
        } else {
            throw new AccessDeniedException("You can't save dish");
        }
        return dish;
    }
}
