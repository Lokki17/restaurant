package ru.restaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserTo;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.UserUtil;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(UserRestController.USER_URL)
public class UserRestController {
    public static final String USER_URL = "/users";

    @Autowired
    UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserToClient> getAll() {
        return UserUtil.toClient(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public UserToClient get(@PathVariable("id") Integer id) {
        return new UserToClient(service.get(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserToClient> create(@Valid @RequestBody User user) {
        User created = service.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(UserUtil.toClient(user));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") Integer id) {
        return service.delete(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToClient update(@Valid @RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        return UserUtil.toClient(service.save(user));
    }

    @PutMapping
    public boolean setRole(@RequestParam("id") Integer id, @RequestParam("role") Role role){
        return service.setRole(id, role);
    }

    @DeleteMapping
    public boolean deleteRole(@RequestParam("id") Integer id, @RequestParam("role") Role role){
        return service.deleteRole(id, role);
    }
}
