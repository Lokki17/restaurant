package ru.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.User;
import ru.restaurant.model.Vote;
import ru.restaurant.dao.RestaurantRepository;
import ru.restaurant.dao.UserRepository;
import ru.restaurant.dao.VoiceRepository;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static ru.restaurant.util.EntityUtil.checkForNull;
import static ru.restaurant.util.VoteUtil.checkRestaurant;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoiceRepository voiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Vote get(int userId) {
        Vote result = voiceRepository.get(userId, LocalDate.now());
        checkForNull(result, "Can't find your today vote");
        return result;
    }

    @Override
    public Collection<Vote> getAll() {
        return voiceRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        return voiceRepository.delete(id, userId);
    }

    @Override
    public Map<Restaurant, Integer> getAllOnDate() {
        Collection<Vote> result = voiceRepository.getAllOnDate(LocalDate.now());
        if (!result.isEmpty()) {
            return VoteUtil.getRestaurantVoiceDistribution(result);
        } else return Collections.emptyMap();
    }


    @Override
    public Vote save(Vote vote, int userId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        TimeUtil.checkLaunchTime(dateTimeNow.toLocalTime());
        Vote savedVote = voiceRepository.get(userId, dateTimeNow.toLocalDate());
        if (savedVote != null) {
            TimeUtil.checkTime(dateTimeNow.toLocalTime());
            vote.setId(savedVote.getId());
        } else {
            vote.setId(null);
        }
        vote.setDate(dateTimeNow.toLocalDate());
        setRestaurant(vote);
        setUser(vote, userId);
        voiceRepository.save(vote, dateTimeNow.toLocalDate(), userId);
        return vote;
    }

    private void setUser(Vote vote, Integer userId) {
        checkRestaurant(vote);
        User savedUser = userRepository.get(userId);
        checkForNull(savedUser, "Can't find user");
        vote.setUser(savedUser);
    }

    private void setRestaurant(Vote vote){
        checkRestaurant(vote);
        Restaurant restaurant = restaurantRepository.get(vote.getRestaurant().getName());
        checkForNull(restaurant, "Can't find restaurant");
        vote.setRestaurant(restaurant);
    }
}
