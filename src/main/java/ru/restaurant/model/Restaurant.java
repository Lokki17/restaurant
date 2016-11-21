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
    private List<Dish> dishes;

    @OneToMany(mappedBy = "dish", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Voice> voice;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Voice> getVoice() {
        return voice;
    }

    public void setVoice(List<Voice> voice) {
        this.voice = voice;
    }
}
