package ru.restaurant.util;

import ru.restaurant.model.User;
import ru.restaurant.to.UserToClient;

import java.util.ArrayList;
import java.util.Collection;

public class UserUtil {

    public static Collection<UserToClient> toClient(Collection<User> users){
        Collection<UserToClient> result = new ArrayList<>();
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
}
