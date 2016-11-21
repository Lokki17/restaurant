package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.VoiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaVoiceRepositoryImpl implements VoiceRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Voice save(Voice voice, LocalDate localDate, int userId) {
        if (voice.isNew()){
            em.persist(voice);
        } else {
            em.merge(voice);
        }

        return voice;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Voice.DELETE, Voice.class).setParameter("voiceId", id).executeUpdate() != 0;
    }

    @Override
    public Voice get(int voiceId, LocalDate localDate) {
        return em.createNamedQuery(Voice.GET, Voice.class)
                .setParameter("voiceId", voiceId)
                .setParameter("date", localDate)
                .getSingleResult();
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDate localDate) {
        return em.createNamedQuery(Voice.GET_ALL, Voice.class).setParameter("date", localDate).getResultList();
    }
}
