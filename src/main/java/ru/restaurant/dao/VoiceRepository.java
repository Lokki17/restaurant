package ru.restaurant.dao;

import ru.restaurant.model.Vote;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.util.Collection;

public interface VoiceRepository {

    Vote save(Vote vote, LocalDate localDate, int userId) throws WrongTimeException;

    boolean delete(int id, int userId);

    Vote get(int userId, LocalDate localDate);

    Collection<Vote> getAllOnDate(LocalDate localDate);

    Collection<Vote> getAll();
}
