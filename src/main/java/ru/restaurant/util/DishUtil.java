package ru.restaurant.util;

import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;
import ru.restaurant.to.RestaurantDishes;

import java.util.*;

public class DishUtil {
    public static Map<Restaurant, Set<Dish>> dishesWithRestaurants(Collection<Dish> dishes){
        Map<Restaurant, Set<Dish>> result = new HashMap<>();
        for (Dish dish : dishes){
            if (!result.containsKey(dish.getRestaurant())){
                result.put(dish.getRestaurant(), new HashSet<>());
            }
            result.get(dish.getRestaurant()).add(dish);
        }
        return result;
    }
}
