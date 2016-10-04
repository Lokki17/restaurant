package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.UserService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class JpaUserService implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<User> getAll(int userId) {
        return null;
    }

    @Override
    public User update(User user, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public User save(User user, int userId) {
        return null;
    }
}
