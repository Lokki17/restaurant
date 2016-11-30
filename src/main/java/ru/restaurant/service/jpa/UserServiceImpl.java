package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.UserService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(int id, int userId) throws NotFoundException {
//        User savedUser = userRepository.checkUser(userId);
//        if (savedUser.isAdmin()) {
            return userRepository.get(userId);
//        } else throw new AccessDeniedException("You can't delete user");
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            return userRepository.delete(id);
        } else {
            throw new AccessDeniedException("You can't delete user");
        }
    }

    @Override
    public Collection<User> getAll(int userId) {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            return userRepository.getAll();
        } else {
            throw new AccessDeniedException("You can't get users list");
        }
    }

    @Override
    public User update(User user, int userId) throws NotFoundException {
        User savedUser = userRepository.checkUser(userId);
        if (savedUser.isAdmin()) {
            return userRepository.save(user);
        } else {
            throw new AccessDeniedException("You can't update user");
        }
    }

    @Override
    public User save(User user, int userId) {
//        User savedUser = userRepository.checkUser(userId);
//        if (savedUser.isAdmin()) {
            return userRepository.save(user);
//        } else {
//            throw new AccessDeniedException("You can't save user");
//        }
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User u = userRepository.getByName(name);
        if (u == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
