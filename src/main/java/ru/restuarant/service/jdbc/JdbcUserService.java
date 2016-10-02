package ru.restuarant.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restuarant.model.User;
import ru.restuarant.repository.UserRepository;
import ru.restuarant.service.UserService;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JdbcUserService implements UserService{

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
