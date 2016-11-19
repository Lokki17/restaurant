package ru.restaurant.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.restaurant.model.Dish;
import ru.restaurant.service.DishService;
import ru.restaurant.web.AuthorizedUser;

import java.time.LocalDate;
import java.util.Collection;

@Controller
public class DishRestController {
    @Autowired
    DishService service;

    public Collection<Dish> getAll() {
        return service.getAllOnDate(LocalDate.now());
    }


    public Dish create(Dish dish) {
        return service.save(dish, AuthorizedUser.getId());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    public void update(Dish dish, int id) {
        dish.setId(id);
        service.save(dish, AuthorizedUser.getId());
    }
}
