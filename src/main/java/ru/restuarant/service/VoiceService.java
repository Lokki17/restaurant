package ru.restuarant.service;

import ru.restuarant.model.Voice;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface VoiceService {
    Voice get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Voice> getAllOnDate(LocalDateTime dateTime);

    Voice update(Voice voice, int userId) throws NotFoundException;

    Voice save(Voice voice, int userId);
}
