package ru.restaurant.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.Dish;
import ru.restaurant.service.DishService;
import ru.restaurant.web.AuthorizedUser;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/dishes")
public class DishRestController {
    @Autowired
    DishService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Dish> getAll() {
        return service.getAllOnDate(LocalDate.now());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@RequestBody Dish dish) {
        return service.save(dish, AuthorizedUser.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        dish.setId(id);
        service.save(dish, AuthorizedUser.getId());
    }
}
