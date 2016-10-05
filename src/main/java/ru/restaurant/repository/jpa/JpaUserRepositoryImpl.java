package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public User save(User user, int userId) {
        User savedDish = em.createNamedQuery(User.GET, User.class)
                .setParameter("userId", User.class)
                .getSingleResult();
        if (savedDish == null) {
            em.persist(user);
        } else {
            user.setId(savedDish.getId());
            em.merge(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE, User.class).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.createNamedQuery(User.GET, User.class).setParameter("userId", id).getSingleResult();
    }
}
