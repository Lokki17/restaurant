package ru.restaurant.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

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
        return userFromResultList(em.createNamedQuery(User.GET, User.class).setParameter("userId", id).getResultList());
    }

    @Override
    public Collection<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Override
    public User getByName(String name) {
        return userFromResultList(em.createNamedQuery(User.GET_BY_NAME, User.class).setParameter("name", name).getResultList());
    }

    private User userFromResultList(List<User> result){
        if (!result.isEmpty()) {
            return result.get(0);
        } else return null;
    }
}
