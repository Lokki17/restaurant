package ru.restaurant.to;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public UserToClient(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("roles") Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
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

    @Override
    public String toString() {
        return "UserToClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
