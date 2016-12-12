package ru.restaurant.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.service.DishService;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(DishRestController.RESTAURANT_URL)
public class DishRestController {
    public static final String RESTAURANT_URL = "/restaurants";
    public static final String DISH_URL = "/dishes";

    @Autowired
    private DishService service;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Dish> getAll() {
//    public Map<Restaurant, Set<Dish>> getAll() {
        return service.getAllOnDate(LocalDate.now());
    }

    @PostMapping(value = "/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable("restaurantId") Integer restaurantId) {
        Dish created = service.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants" + restaurantId + "/dishes/" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/dishes/{id}")
    @Secured("ROLE_ADMIN")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping(value = "/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
//    public Dish update(@Valid @RequestBody Dish dish, @PathVariable("restaurantId") Integer restaurantId) {
    public Dish update(@Valid @RequestBody Dish dish, @PathVariable("id") Integer id, @PathVariable("restaurantId") Integer restaurantId) {
        dish.setId(id);
        return service.update(dish, restaurantId);
    }
}
