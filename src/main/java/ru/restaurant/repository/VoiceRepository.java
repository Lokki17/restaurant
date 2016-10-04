package ru.restaurant.repository;

import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceRepository {
    // null if updated meal do not belong to userId
    Voice save(Voice meal, LocalDate localDate, int userId) throws WrongTimeException;

    // false if meal do not belong to userId
    //boolean delete(int id, int userId);

    // null if meal do not belong to userId
    //Voice get(int id, int userId);

    // ORDERED dateTime
    Collection<Voice> getAllOnDate(LocalDate localDate);

    // ORDERED dateTime
    //Collection<Voice> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
