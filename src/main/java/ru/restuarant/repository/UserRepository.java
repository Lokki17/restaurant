package ru.restuarant.repository;

import ru.restuarant.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserRepository {
    // null if updated meal do not belong to userId
    User save(User user, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    User get(int id, int userId);

    // ORDERED dateTime
    //Collection<User> getAll(int userId);

    // ORDERED dateTime
    //Collection<User> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
