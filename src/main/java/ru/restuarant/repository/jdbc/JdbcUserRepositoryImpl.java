package ru.restuarant.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.restuarant.model.User;
import ru.restuarant.repository.UserRepository;

@Repository
public class JdbcUserRepositoryImpl implements UserRepository{
    @Override
    public User save(User user, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public User get(int id, int userId) {
        return null;
    }
}
