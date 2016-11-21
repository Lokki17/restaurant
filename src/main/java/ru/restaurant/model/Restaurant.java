package ru.restaurant.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:restuarantId"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r WHERE r.id=:restuarantId")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.get";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Dish> dishs;

    public List<Dish> getDishes() {
        return dishs;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishs = dishes;
    }
}
