package ru.restaurant.service;

import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;

public interface UserService {
    User get(int id) throws NotFoundException;

    boolean delete(int id) throws NotFoundException;

    Collection<User> getAll();

    User update(User user) throws NotFoundException;

//    User save(User user, int userId);

    User save(User user);

    boolean setRole(Integer id, Role role);

    boolean deleteRole(Integer id, Role role);

//    boolean checkUser(String name);
}
