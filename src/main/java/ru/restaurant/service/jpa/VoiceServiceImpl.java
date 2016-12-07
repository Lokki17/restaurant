package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.EntityUtil;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.UserUtil;
import ru.restaurant.util.VoiceUtil;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static ru.restaurant.util.EntityUtil.checkForNull;
import static ru.restaurant.util.VoiceUtil.checkRestaurant;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Voice get(int userId) {
        Voice result = voiceRepository.get(userId, LocalDate.now());
        checkForNull(result, "Can't find your today voice");
        return result;
    }

    @Override
    public Collection<Voice> getAll() {
        return voiceRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        return voiceRepository.delete(id, userId);
    }

    @Override
    public Map<Restaurant, Integer> getAllOnDate() {
        Collection<Voice> result = voiceRepository.getAllOnDate(LocalDate.now());
        if (!result.isEmpty()) {
            return VoiceUtil.getRestaurantVoiceDistribution(result);
        } else return Collections.emptyMap();
    }


    @Override
    public Voice save(Voice voice, int userId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        TimeUtil.checkLaunchTime(dateTimeNow.toLocalTime());
        Voice savedVoice = voiceRepository.get(userId, dateTimeNow.toLocalDate());
        if (savedVoice != null) {
            TimeUtil.checkTime(dateTimeNow.toLocalTime());
            voice.setId(savedVoice.getId());
        } else {
            voice.setId(null);
        }
        voice.setDate(dateTimeNow.toLocalDate());
        setRestaurant(voice);
        setUser(voice, userId);
        return voiceRepository.save(voice, dateTimeNow.toLocalDate(), userId);
    }

    private void setUser(Voice voice, Integer userId) {
        checkRestaurant(voice);
        User savedUser = userRepository.get(userId);
        checkForNull(savedUser, "Can't find user");
        voice.setUser(savedUser);
    }

    private void setRestaurant(Voice voice){
        checkRestaurant(voice);
        Restaurant restaurant = restaurantRepository.get(voice.getRestaurant().getName());
        checkForNull(restaurant, "Can't find restaurant");
        voice.setRestaurant(restaurant);
    }
}
