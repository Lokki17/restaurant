package ru.restaurant.model;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.user WHERE v.date=:date ORDER BY v.id"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:voteId AND v.user.id=:userId"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.user WHERE v.date=:date AND v.user.id=:userId")
//        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:voiceId AND v.date=:date AND v.user.id=:userId")
})
@Entity
@Table(name = "votes")
public class Vote extends DatedEntity {
    public static final String GET_ALL = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String GET = "Vote.get";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", unique = true, nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date, Restaurant restaurant, User user) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(LocalDate date, Restaurant restaurant, User user) {
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
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                "}";
    }
}
