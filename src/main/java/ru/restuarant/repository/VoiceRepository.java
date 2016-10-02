package ru.restuarant.repository;

import ru.restuarant.model.Voice;

import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceRepository {
    // null if updated meal do not belong to userId
    Voice save(Voice meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Voice get(int id, int userId);

    // ORDERED dateTime
    Collection<Voice> getAll(int userId);

    // ORDERED dateTime
    Collection<Voice> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
