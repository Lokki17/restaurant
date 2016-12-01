package ru.restaurant.to;

import ru.restaurant.model.Role;
import ru.restaurant.model.User;

import java.util.Set;

public class UserToClient {

    private final Integer id;
    private final String name;
    private final Set<Role> roles;

    public UserToClient(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.roles = user.getRoles();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
