package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.UserService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class JpaUserService implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(int id, int userId) throws NotFoundException {
        return userRepository.get(id);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User user = userRepository.get(userId);
        if (user.getRole().contains(Role.ADMIN)){
            return userRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete user");
        }
    }

    @Override
    public Collection<User> getAll(int userId) {
        User user = userRepository.get(userId);
        if (user.getRole().contains(Role.ADMIN)){
            return userRepository.getAll();
        } else {
            throw new AccessDeniedException("You can't delete user");
        }
    }

    @Override
    public User update(User user, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        if (savedUser.getRole().contains(Role.ADMIN)){
            userRepository.save(user);
        } else {
            throw new AccessDeniedException("You can't update user");
        }
        return user;
    }

    @Override
    public User save(User user, int userId) {
        User savedUser = userRepository.get(userId);
        if (savedUser.getRole().contains(Role.ADMIN)){
            userRepository.save(user);
        } else {
            throw new AccessDeniedException("You can't save user");
        }
        return user;
    }
}
