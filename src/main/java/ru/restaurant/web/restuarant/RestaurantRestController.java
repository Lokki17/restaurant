package ru.restaurant.web.restuarant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.Restaurant;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {
    @Autowired
    RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return service.save(restaurant, AuthorizedUser.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") Integer id) {
        restaurant.setId(id);
        service.save(restaurant, AuthorizedUser.getId());
    }
}
