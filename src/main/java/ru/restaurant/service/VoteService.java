package ru.restaurant.service;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Vote;

import java.util.Collection;
import java.util.Map;

public interface VoteService {
    Vote get(int userId);

    Collection<Vote> getAll();

    boolean delete(int id, int userId);

    Map<Restaurant, Integer> getAllOnDate();

    Vote save(Vote vote, int userId);
}
