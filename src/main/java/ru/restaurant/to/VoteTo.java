package ru.restaurant.to;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Vote;

import java.time.LocalDate;

public class VoteTo {

    private Integer id;

    private Restaurant restaurant;

    private String userName;

    private Integer userId;

    private LocalDate date;

    public VoteTo() {
    }

    public VoteTo(Vote vote) {
        this.id = vote.getId();
        this.restaurant = vote.getRestaurant();
        this.userName = vote.getUser().getName();
        this.userId = vote.getUser().getId();
        this.date = vote.getDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
