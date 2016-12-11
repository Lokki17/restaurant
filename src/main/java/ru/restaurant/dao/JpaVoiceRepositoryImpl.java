package ru.restaurant.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.model.Vote;

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
    public Vote save(Vote vote, LocalDate localDate, int userId) {
        if (!vote.isNew() && get(userId, localDate) == null){
            return null;
        }
        if (vote.isNew()){
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Vote.DELETE).setParameter("voteId", id).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public Vote get(int userId, LocalDate localDate) {
        List<Vote> result = em.createNamedQuery(Vote.GET, Vote.class)
                .setParameter("userId", userId)
                .setParameter("date", localDate)
                .getResultList();
        if (!result.isEmpty()){
            return result.get(0);
        } else return null;
    }

    @Override
    public Collection<Vote> getAllOnDate(LocalDate localDate) {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).setParameter("date", localDate).getResultList();
    }

    @Override
    public Collection<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).setParameter("date", LocalDate.now()).getResultList();
    }
}
