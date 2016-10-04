package ru.restaurant.service;

import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceService {
    Voice get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Voice> getAllOnDate(LocalDateTime dateTime);

    Voice save(Voice voice, int userId);
}
