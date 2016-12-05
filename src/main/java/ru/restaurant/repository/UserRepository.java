package ru.restaurant.repository;

import ru.restaurant.model.User;

import java.util.Collection;

public interface UserRepository {
    // null if updated meal do not belong to userId
    User save(User user);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    User get(int id);

    Collection<User> getAll();

//    User checkUser(Integer userId);

    User getByName(String name);

    // ORDERED dateTime
    //Collection<User> getAll(int userId);

    // ORDERED dateTime
    //Collection<User> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
