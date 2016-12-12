package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r ORDER BY r.id"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:restuarantId"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT r FROM Restaurant r WHERE r.id=:restuarantId"),
        @NamedQuery(name = Restaurant.GET_BY_NAME, query = "SELECT r FROM Restaurant r WHERE r.name=:restaurantName")
})
@Entity
@Table(name = "restaurants")
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant extends NamedEntity{

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.get";
    public static final String GET_BY_NAME = "Restaurant.get.by.name";

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(String name) {
        this.id = null;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant (" +
                "id=" + id +
                ", name=" + name +
                ')';
    }
}
