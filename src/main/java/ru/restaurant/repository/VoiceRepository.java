package ru.restaurant.repository;

import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceRepository {

    Voice save(Voice voice, LocalDate localDate, int userId) throws WrongTimeException;

    boolean delete(int id, int userId);

    Voice get(int userId, LocalDate localDate);

    Collection<Voice> getAllOnDate(LocalDate localDate);

    Collection<Voice> getAll();
}
