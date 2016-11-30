package ru.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserTo;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    UserService service;

    @GetMapping
    public String root() {
        return "redirect:/index";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@Valid @RequestBody User user) {
        if (service.checkUser(user.getName())) {
            service.save(user, AuthorizedUser.getId());
            return "redirect:/";
        } else throw new RuntimeException("User already registered");
    }
}
