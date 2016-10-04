package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager em;

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
