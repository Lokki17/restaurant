package ru.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.exception.UserExistsException;

import javax.validation.Valid;

@RestController
public class RootController {

    @Autowired
    UserService service;

    @GetMapping
    public String root() {
        return "redirect:/index";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToClient create(@Valid @RequestBody User user) {
//        if (service.checkUser(user.getName())) {
            return new UserToClient(service.save(user));
//            return "redirect:/";
//        } else throw new UserExistsException("User already registered");
    }
}
