package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.RestaurantRepository;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.VoiceUtil;
import ru.restaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        return checkVoice(voiceRepository.get(userId, LocalDate.now()), "Can't find your today voice");
    }

    @Override
    public Collection<Voice> getAll() {
        return voiceRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
/*        User savedUser = userRepository.get(userId);
        Assert.notNull(savedUser, "can't find voice");*/
        return voiceRepository.delete(id, userId);
    }

    @Override
    public Map<Restaurant, Integer> getAllOnDate() {
        Collection<Voice> result = voiceRepository.getAllOnDate(LocalDate.now());
        if (!result.isEmpty()) {
            return VoiceUtil.getRestaurantVoiceDistribution(result);
        } else return Collections.emptyMap();
    }


    @Override //TODO проверить!!!
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
        VoiceUtil.checkId(voice);
        User savedUser = userRepository.get(userId);
        Assert.notNull(savedUser, "User not found");
        voice.setUser(savedUser);
    }

    private void setRestaurant(Voice voice){
        VoiceUtil.checkId(voice);
        Restaurant restaurant = restaurantRepository.get(voice.getRestaurant().getName());
        Assert.notNull(restaurant, "Restaurant not found");
        voice.setRestaurant(restaurant);
    }

    private Voice checkVoice(Voice result, String message) {
        if (result == null){
            throw new NotFoundException(message);
        }
        return result;
    }
}
