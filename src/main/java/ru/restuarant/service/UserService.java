package ru.restuarant.service;

import ru.restuarant.model.User;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface UserService {
    User get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<User> getAll(int userId);

    User update(User user, int userId) throws NotFoundException;

    User save(User user, int userId);
}
