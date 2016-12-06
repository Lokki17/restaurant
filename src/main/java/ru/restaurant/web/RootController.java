package ru.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.UserUtil;
import ru.restaurant.util.exception.UserExistsException;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class RootController {

    @Autowired
    UserService service;

    @GetMapping
    public String root() {
        return "redirect:/index";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserToClient> create(@Valid @RequestBody User user) {
        UserUtil.checkForAdmin(user);
        UserToClient created = new UserToClient(service.save(user));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(UserUtil.toClient(user));
//        if (service.checkUser(user.getName())) {
//            return "redirect:/";
//        } else throw new UserExistsException("User already registered");
    }
}
