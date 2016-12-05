package ru.restaurant.to;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;

import java.time.LocalDate;

public class VoiceTo {

    private Integer id;

    private Restaurant restaurant;

    private String userName;

    private Integer userId;

    private LocalDate date;

    public VoiceTo() {
    }

    public VoiceTo(Voice voice) {
        this.id = voice.getId();
        this.restaurant = voice.getRestaurant();
        this.userName = voice.getUser().getName();
        this.userId = voice.getUser().getId();
        this.date = voice.getDate();
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
        return "VoiceTo{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
