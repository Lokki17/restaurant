package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Voice get(int id, int userId) throws NotFoundException {
        return voiceRepository.get(id, userId, LocalDate.now());
    }

    @Override
    public Collection<Voice> getAll() {
        return voiceRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        Objects.isNull(savedUser);
        if (savedUser.getRoles().contains(Role.ADMIN)){
            return voiceRepository.delete(id);
        }

        return false;
    }

    @Override
    public Map<Restaurant, Integer> getAllOnDate() {
        return VoiceUtil.getRestaurantVoiceDistribution(voiceRepository.getAllOnDate(LocalDate.now()));
    }


    @Override //TODO проверить!!!
    public Voice save(Voice voice, int userId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (TimeUtil.checkLaunchTime(dateTimeNow.toLocalTime())) {
            throw new WrongTimeException("Launch time is gone");
        }
        if (voice.getId() != null) {
            if (TimeUtil.checkTime(dateTimeNow.toLocalTime())) {
                throw new WrongTimeException("You have made your choice today");
            }
        }

        return voiceRepository.save(voice, dateTimeNow.toLocalDate(), userId);
    }
}
