package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:restuarantId"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT r FROM Restaurant r WHERE r.id=:restuarantId"),
        @NamedQuery(name = Restaurant.GET_BY_NAME, query = "SELECT r FROM Restaurant r WHERE r.name=:restaurantName")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.get";
    public static final String GET_BY_NAME = "Restaurant.get.by.name";

    @Override
    public String toString() {
        return name;
    }

    /*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JsonIgnore
    private List<Dish> dishes;*/

/*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JsonIgnore
    private List<Voice> voices;*/
/*
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voice) {
        this.voices = voice;
    }*/
}
