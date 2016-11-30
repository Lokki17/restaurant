package ru.restaurant.to;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;

public class VoiceTo {

    private Integer id;

    private Restaurant restaurant;

    private String userName;

    private Integer userId;

    public VoiceTo(Voice voice) {
        this.id = voice.getId();
        this.restaurant = voice.getRestaurant();
        this.userName = voice.getUser().getName();
        this.userId = voice.getUser().getId();
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
}
