package ru.restaurant.to;

import ru.restaurant.model.Dish;
import ru.restaurant.model.Restaurant;

import java.util.HashSet;
import java.util.Set;

public class RestaurantDishes {
    private Restaurant restaurant;
    private Set<Dish> dishes = new HashSet<>();

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public boolean addDish(Dish dish){
        return dishes.add(dish);
    }
}
