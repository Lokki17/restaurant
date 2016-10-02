package ru.restuarant.repository;

import ru.restuarant.model.Restuarant;

import java.time.LocalDateTime;
import java.util.Collection;

public interface RestuarantRepository {
    // null if updated meal do not belong to userId
    Restuarant save(Restuarant meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Restuarant get(int id, int userId);

    // ORDERED dateTime
    Collection<Restuarant> getAll(int userId);

    // ORDERED dateTime
    Collection<Restuarant> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
