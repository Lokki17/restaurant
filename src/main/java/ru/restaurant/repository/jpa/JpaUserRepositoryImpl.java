package ru.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (!user.isNew() && get(user.getId()) == null) {
            return null;
        }
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE).setParameter("userId", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        List<User> result = em.createNamedQuery(User.GET, User.class).setParameter("userId", id).getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        } else return null;
    }

    @Override
    public Collection<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

/*    @Override
    public User checkUser(Integer userId) {
        User savedUser = get(userId);
        Assert.notNull(savedUser, "can't find user");
        return savedUser;
    }*/

    @Override
    public User getByName(String name) {
        List<User> result = em.createNamedQuery(User.GET_BY_NAME, User.class).setParameter("name", name).getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        } else return null;
    }
}
