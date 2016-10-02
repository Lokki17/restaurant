package ru.restuarant.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restuarant.model.Voice;
import ru.restuarant.repository.VoiceRepository;
import ru.restuarant.service.VoiceService;
import ru.restuarant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class JdbcVoiceService implements VoiceService{

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
    public Voice update(Voice voice, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public Voice save(Voice voice, int userId) {
        return null;
    }
}
