package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.Assert;
import ru.restaurant.util.EntityUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant WHERE d.date=:date ORDER BY d.restaurant.name, d.name"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:dishId"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant WHERE d.id=:dishId")
})
//@NamedEntityGraph(name = Dish.GRAPH, attributeNodes = {@NamedAttributeNode("restaurant")})
@Entity
@Table(name = "dishes")
//@JsonIgnoreProperties({"restaurant"})
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dish extends DatedEntity implements Comparable {
//    public static final String GRAPH = "Dishes.restaurant";

    public static final String GET_ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET = "Dish.get";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
//    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "price")
    @NotNull
    private BigInteger price;

    @Column(name = "name")
    @NotEmpty
    private String name;

    public Dish() {
    }

    public Dish(Integer id, LocalDate date, String name, BigInteger price, Restaurant restaurant) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(LocalDate date, String name, BigInteger price, Restaurant restaurant) {
        this.id = null;
        this.date = date;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public boolean isNew() {
        return id == null;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", date=" + date +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        Dish that = (Dish) o;
        Assert.notNull(this.getId(), "It's new dish");
        Assert.notNull(that.getId(), "It's new dish");
//        EntityUtil.checkForNull(o, "Nothing to compare");
        if (!this.name.equals(that.name)) {
            return this.name.compareTo(that.name);
        } else {
            return this.id.compareTo(that.getId());
        }
    }
}
