package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JpaVoiceService implements VoiceService{

    @Autowired
    VoiceRepository voiceRepository;

    @Override
    public Voice get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDateTime dateTime) {
        return null;
    }


    @Override
    public Voice save(Voice voice, int userId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (dateTimeNow.toLocalTime().isAfter(TimeUtil.STOP_TIME)){
            throw new WrongTimeException();
        }
        return null;
    }
}
