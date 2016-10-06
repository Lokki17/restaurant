package ru.restaurant.service;

import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceService {
    Voice get(LocalDate localDate, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    Collection<Voice> getAllOnDate(LocalDate dateTime);

    Voice save(Voice voice, int userId);
}
