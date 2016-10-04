package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.VoiceRepository;
import ru.restaurant.util.TimeUtil;
import ru.restaurant.util.exception.WrongTimeException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Repository
public class JpaVoiceRepositoryImpl implements VoiceRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Voice save(Voice voice, LocalDate localDate, int userId) {

        Voice savedVoice = em.createNamedQuery(Voice.GET, Voice.class)
                .setParameter("dishId", voice.getId())
                .setParameter("dateTime", localDate)
                .getSingleResult();
        if (savedVoice == null){
            em.persist(voice);
        } else {
            voice.setId(savedVoice.getId());
            em.merge(voice);
        }

        return voice;
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDate localDate) {
        return em.createNamedQuery(Voice.GET_ALL, Voice.class).setParameter("dateTime", localDate).getResultList();
    }
}
