package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
public class JpaVoiceService implements VoiceService {

    @Autowired
    VoiceRepository voiceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Voice get(LocalDate localDate, int userId) throws NotFoundException {
        return voiceRepository.get(userId, localDate);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.get(userId);
        if (savedUser != null && savedUser.getRole().contains(Role.ADMIN)){
            return voiceRepository.delete(id);
        }

        return false;
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDate dateTime) {
        return voiceRepository.getAllOnDate(dateTime);
    }


    @Override //TODO проверить!!!
    public Voice save(Voice voice, int userId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (TimeUtil.checkLaunchTime(dateTimeNow.toLocalTime())) {
            throw new WrongTimeException("Launch time is gone");
        }
        Voice savedVoice = get(dateTimeNow.toLocalDate(), userId);
        if (savedVoice != null) {
            if (TimeUtil.checkTime(dateTimeNow.toLocalTime())) {
                throw new WrongTimeException("You have made your choice");
            }
        }


        return voiceRepository.save(voice, dateTimeNow.toLocalDate(), userId);
    }
}
