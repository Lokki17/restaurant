package ru.restaurant.model;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Voice.GET_ALL, query = "SELECT v FROM Voice v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.user WHERE v.date=:date"),
        @NamedQuery(name = Voice.DELETE, query = "DELETE FROM Voice v WHERE v.id=:voiceId AND v.user.id=:userId"),
        @NamedQuery(name = Voice.GET, query = "SELECT v FROM Voice v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.user WHERE v.date=:date AND v.user.id=:userId")
//        @NamedQuery(name = Voice.GET, query = "SELECT v FROM Voice v WHERE v.id=:voiceId AND v.date=:date AND v.user.id=:userId")
})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    public static final String GET_ALL = "Voice.getAll";
    public static final String DELETE = "Voice.delete";
    public static final String GET = "Voice.get";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", unique = true, nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Voice() {
    }

    public Voice(Integer id, LocalDate date, Restaurant restaurant, User user) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Voice(LocalDate date, Restaurant restaurant, User user) {
        this.id = null;
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "id=" + id +
                ", date=" + date +
/*                ", restaurant=" + restaurant +
                ", user=" + user +*/
                '}';
    }
}
