package ru.restuarant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restuarant.model.Voice;
import ru.restuarant.repository.VoiceRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaVoiceRepositoryImpl implements VoiceRepository{
    @Override
    public Voice save(Voice meal, LocalDateTime dateTime, int userId) {
        return null;
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDateTime dateTime) {
        return null;
    }
}
