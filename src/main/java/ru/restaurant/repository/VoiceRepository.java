package ru.restaurant.repository;

import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceRepository {
    // null if updated meal do not belong to userId
    Voice save(Voice voice, LocalDate localDate, int userId) throws WrongTimeException;

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Voice get(int userId, LocalDate localDate);
//    Voice get(int voiceId, int userId, LocalDate localDate);

    // ORDERED dateTime
    Collection<Voice> getAllOnDate(LocalDate localDate);

    Collection<Voice> getAll();

    // ORDERED dateTime
    //Collection<Voice> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
