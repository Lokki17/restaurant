package ru.restaurant.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.repository.UserRepository;
import ru.restaurant.service.UserService;

import ru.restaurant.util.EntityUtil;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;
import java.util.Set;

import static ru.restaurant.util.EntityUtil.checkForNull;
import static ru.restaurant.util.UserUtil.prepareToSave;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(int id) throws NotFoundException {
        User result = userRepository.get(id);
        checkUser(result, "can't find request user");
        return result;
    }

    @Override
    public boolean delete(int id) {
        Boolean result = userRepository.delete(id);
        if (result) {
            return true;
        } else throw new NotFoundException("Can't find request user");
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(User user) {
        return checkUser(userRepository.save(prepareToSave(user)), "Can't find request user");
    }

    @Override
    public User save(User user) {
        return checkUser(userRepository.save(prepareToSave(user)), "user " + user.getName() + "did't save");
    }

    @Override
    public boolean setRole(Integer id, Role role) {
        User result = userRepository.get(id);
        checkUser(result, "can't find request user");
        if (result.getRoles().add(role)) {
            User savedUser = userRepository.save(result);
            return savedUser != null;
        } else return false;
    }

    @Override
    public boolean deleteRole(Integer id, Role role) {
        User result = userRepository.get(id);
        checkUser(result, "can't find request user");
        if (result.getRoles().remove(role)) {
            User savedUser = userRepository.save(result);
            return savedUser != null;
        } else return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username);
        checkUser(user, "User " + username + " is not found");
        return new AuthorizedUser(user);
    }

    private User checkUser(User result, String message) {
        checkForNull(result, message);
        return result;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }
}
