package ru.restaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() {
        return service.getAll(AuthorizedUser.getId());
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") Integer id) {
        return service.get(id, AuthorizedUser.getId());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        return service.save(user, AuthorizedUser.getId());
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        return service.save(user, AuthorizedUser.getId());
    }
}
