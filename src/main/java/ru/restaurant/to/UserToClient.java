package ru.restaurant.to;

import ru.restaurant.model.User;

public class UserToClient {

    private final Integer id;
    private final String name;

    public UserToClient(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
