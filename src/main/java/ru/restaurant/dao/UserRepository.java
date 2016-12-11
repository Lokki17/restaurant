package ru.restaurant.dao;

import ru.restaurant.model.User;

import java.util.Collection;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    Collection<User> getAll();

    User getByName(String name);
}
