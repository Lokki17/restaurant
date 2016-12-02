package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Map;

public interface VoiceService {
    Voice get(int id, int userId) throws NotFoundException;

    Collection<Voice> getAll();

    boolean delete(int id, int userId) throws NotFoundException;

    Map<Restaurant, Integer> getAllOnDate();

    Voice save(Voice voice, int userId);
}
