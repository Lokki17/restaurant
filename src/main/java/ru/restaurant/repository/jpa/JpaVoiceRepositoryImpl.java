package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.VoiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaVoiceRepositoryImpl implements VoiceRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Voice save(Voice meal, LocalDateTime dateTime, int userId) {
        return null;
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDateTime dateTime) {
        return null;
    }
}
