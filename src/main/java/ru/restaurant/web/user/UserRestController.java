package ru.restaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserTo;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<UserTo> create(@Valid @RequestBody User user) {
        User created = service.save(user, AuthorizedUser.getId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(new UserTo(user));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") Integer id) {
        return service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@Valid @RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        return service.save(user, AuthorizedUser.getId());
    }
}
