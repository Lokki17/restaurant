package ru.restaurant.service;

import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

public interface UserService {
    User get(int id, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    Collection<User> getAll(int userId);

    User update(User user, int userId) throws NotFoundException;

    User save(User user, int userId);

    User save(User user);

    boolean setRole(Integer id, Role role);

    boolean deleteRole(Integer id, Role role);

//    boolean checkUser(String name);
}
