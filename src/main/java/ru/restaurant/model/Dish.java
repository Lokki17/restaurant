package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant WHERE d.date=:date ORDER BY d.restaurant.name, d.name"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:dishId"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:dishId")
})
//@NamedEntityGraph(name = Dish.GRAPH, attributeNodes = {@NamedAttributeNode("restaurant")})
@Entity
@Table(name = "dishes")
public class Dish extends DatedEntity implements Comparable{
//    public static final String GRAPH = "Dishes.restaurant";

    public static final String GET_ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET = "Dish.get";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

/*    @OneToMany(mappedBy = "dish", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Voice> voice;*/

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "name")
    @NotNull
    private String name;

    public boolean isNew(){
        return id == null;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

/*    public List<Voice> getVoice() {
        return voice;
    }

    public void setVoice(List<Voice> voice) {
        this.voice = voice;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Dish that = (Dish) o;
        if (!this.name.equals(that.name)){
            return this.name.compareTo(that.name);
        } else {
            return this.id.compareTo(that.getId());
        }
    }
}
