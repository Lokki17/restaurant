package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class JpaUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public User save(User user) {
/*        User savedDish = em.createNamedQuery(User.GET, User.class)
                .setParameter("userId", User.class)
                .getSingleResult();*/
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE, User.class).setParameter("userId", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.createNamedQuery(User.GET, User.class).setParameter("userId", id).getSingleResult();
    }

    @Override
    public Collection<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }
}
