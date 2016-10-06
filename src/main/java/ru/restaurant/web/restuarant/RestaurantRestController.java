package ru.restaurant.web.restuarant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.restaurant.model.Restaurant;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@Controller
public class RestaurantRestController {
    @Autowired
    RestaurantService service;

    public Collection<Restaurant> getAll() {
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        return service.save(restaurant, AuthorizedUser.getId());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    public void update(Restaurant restaurant, int id) {
        restaurant.setId(id);
        service.save(restaurant, AuthorizedUser.getId());
    }
}
