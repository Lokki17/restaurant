package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.service.VoiceService;
import ru.restaurant.to.RestaurantVoices;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.VoiceUtil;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.util.exception.WrongTimeException;
import ru.restaurant.web.AuthorizedUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Voice get(int id, int userId) {
        return voiceRepository.get(userId, LocalDate.now());
//        return voiceRepository.get(id, userId, LocalDate.now());
    }

    @Override
    public Collection<Voice> getAll() {
        return voiceRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        User savedUser = userRepository.get(userId);
        Assert.notNull(savedUser, "can't find user");
        return savedUser.isAdmin() && voiceRepository.delete(id);
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
        return voiceRepository.save(voice, dateTimeNow.toLocalDate(), userId);
    }
}
