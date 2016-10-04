package ru.restaurant.repository;

import ru.restaurant.model.Voice;

import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceRepository {
    // null if updated meal do not belong to userId
    Voice save(Voice meal, LocalDateTime dateTime, int userId);

    // false if meal do not belong to userId
    //boolean delete(int id, int userId);

    // null if meal do not belong to userId
    //Voice get(int id, int userId);

    // ORDERED dateTime
    Collection<Voice> getAllOnDate(LocalDateTime dateTime);

    // ORDERED dateTime
    //Collection<Voice> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
