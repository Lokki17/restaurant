package ru.restaurant.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;
import ru.restaurant.service.DishService;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.to.RestaurantDishes;
import ru.restaurant.util.DishUtil;
import ru.restaurant.util.EntityUtil;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/dishes")
public class DishRestController {
    @Autowired
    DishService service;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Set<Dish>> getAll() {
        return service.getAllOnDate(LocalDate.now());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @RequestParam("id") Integer restaurantId) {
//        setRestaurant(dish);
        Dish created = service.save(dish, restaurantId, AuthorizedUser.getId());
//        return service.save(dish, restaurantId, AuthorizedUser.getId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id, AuthorizedUser.getId());
    }

/*    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable Integer id) {
        return service.get(id);
    }*/

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@Valid @RequestBody Dish dish, @PathVariable("id") Integer id, @RequestParam("restaurantId") Integer restaurantId) {
        dish.setId(id);
//        setRestaurant(dish);
//        dish.setRestaurant(restaurantService.get(restaurantId));
        return service.save(dish, restaurantId, AuthorizedUser.getId());
    }

/*    private void setRestaurant(Dish dish){
//        DishUtil.checkId(dish);
        Restaurant restaurant = restaurantService.getByName(dish.getRestaurant().getName());
//        Restaurant restaurant = restaurantService.get(dish.getRestaurant().getId());
        if (restaurant != null){
            dish.setRestaurant(restaurant);
        } else {
            throw new NotFoundException("Not found Restaurant");
        }
    }*/
}
