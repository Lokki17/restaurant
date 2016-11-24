package ru.restaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@RestController("/users")
public class UserRestController {
    @Autowired
    UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() {
        return service.getAll(AuthorizedUser.getId());
    }

    @GetMapping(name = "/{id}")
    public User get(int id) {
        return service.get(id, AuthorizedUser.getId());
    }

    public User create(User user) {
        return service.save(user, AuthorizedUser.getId());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    public void update(User user, int id) {
        user.setId(id);
        service.save(user, AuthorizedUser.getId());
    }
}
