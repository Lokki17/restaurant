package ru.restaurant.to;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;

import java.util.ArrayList;
import java.util.List;

public class RestaurantVoices {
    private Restaurant restaurant;
    private List<Voice> voices = new ArrayList<>();

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Voice> getDishes() {
        return voices;
    }

    public void setDishes(List<Voice> dishes) {
        this.voices = dishes;
    }

    public Integer voicesCount(){
        return voices.size();
    }

    public boolean addVoice(Voice voice){
        return voices.add(voice);
    }
}
