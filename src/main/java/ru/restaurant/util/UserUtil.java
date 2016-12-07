package ru.restaurant.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.Assert;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserUtil {

    private UserUtil() {
    }

    public static List<UserToClient> toClient(Collection<User> users){
        List<UserToClient> result = new ArrayList<>();
        users.stream().forEach(user -> result.add(new UserToClient(user)));
        return result;
    }

    public static UserToClient toClient(User user){
        return new UserToClient(user);
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        return user;
    }

    public static void checkForAdmin(User user){
        Assert.notNull(user.getRoles(), "Set of roles is empty");
        if (user.getRoles().contains(Role.ROLE_ADMIN)){
            throw new AccessDeniedException("You can't get admin role");
        }
    }

    public static void checkForNull(User user){
        if (user == null){
            throw new NotFoundException("Can't find request user");
        }
    }
}
