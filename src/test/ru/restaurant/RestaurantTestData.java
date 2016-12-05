package ru.restaurant;

import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Restaurant;

import java.util.Objects;

import static ru.restaurant.model.BaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final Integer RESTAURANT_ID = START_SEQ + 3;

    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName()));

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Минутка");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Для всей семьи");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "Самый лучший ресторан");
    public static final Restaurant RESTAURANT_4 = new Restaurant(RESTAURANT_ID + 3, "The Best");

    public static Restaurant getUpdated(){
        return new Restaurant(RESTAURANT_ID, "Хутка и смачна");
    }

    public static Restaurant getCreated(){
        return new Restaurant("McDonalds");
    }
}
