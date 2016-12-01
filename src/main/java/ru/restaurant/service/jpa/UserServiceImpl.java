package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.UserService;
import ru.restaurant.util.exception.AccessDeniedException;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(int id, int userId) throws NotFoundException {
        User result = userRepository.get(id);
        Assert.notNull(result, "can't find request user");
        return result;
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        return userRepository.delete(id);
    }

    @Override
    public Collection<User> getAll(int userId) {
        return userRepository.getAll();
    }

    @Override
    public User update(User user, int userId) throws NotFoundException {
        User result = userRepository.save(user);
        Assert.notNull(result, "can't find request user");
        return result;
    }

    @Override
    public User save(User user, int userId) {
        User result = userRepository.save(user);
        Assert.notNull(result, "can't find request user");
        return result;
    }

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        Assert.notNull(user, "user " + user.getName() + "did't saved");
        return savedUser;
    }

    @Override
    public boolean checkUser(String name) {
        try {
            loadUserByUsername(name);
        } catch (UsernameNotFoundException e){
            return true;
        } return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
