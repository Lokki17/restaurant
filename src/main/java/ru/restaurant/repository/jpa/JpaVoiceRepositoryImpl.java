package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.model.Voice;
import ru.restaurant.repository.VoiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoiceRepositoryImpl implements VoiceRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Voice save(Voice voice, LocalDate localDate, int userId) {
        if (!voice.isNew() && get(userId, localDate) == null){
            return null;
        }
        if (voice.isNew()){
            em.persist(voice);
            return voice;
        } else {
            return em.merge(voice);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Voice.DELETE).setParameter("voiceId", id).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public Voice get(int userId, LocalDate localDate) {
        List<Voice> result = em.createNamedQuery(Voice.GET, Voice.class)
                .setParameter("userId", userId)
                .setParameter("date", localDate)
                .getResultList();
        if (!result.isEmpty()){
            return result.get(0);
        } else return null;
    }

    @Override
    public Collection<Voice> getAllOnDate(LocalDate localDate) {
        return em.createNamedQuery(Voice.GET_ALL, Voice.class).setParameter("date", localDate).getResultList();
    }

    @Override
    public Collection<Voice> getAll() {
        return em.createNamedQuery(Voice.GET_ALL, Voice.class).setParameter("date", LocalDate.now()).getResultList();
    }
}
