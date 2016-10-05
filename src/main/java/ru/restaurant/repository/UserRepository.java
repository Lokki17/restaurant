package ru.restaurant.repository;

import ru.restaurant.model.User;

public interface UserRepository {
    // null if updated meal do not belong to userId
    User save(User user, int userId);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    User get(int id);

    // ORDERED dateTime
    //Collection<User> getAll(int userId);

    // ORDERED dateTime
    //Collection<User> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
