package ru.restuarant.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;

public class Dish extends DatedEntity {
    @Column(name = "price")
    @NotEmpty
    private Double price;

    @Column(name = "restaurant_id")
    private int restaurantId;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
