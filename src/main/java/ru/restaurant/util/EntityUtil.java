package ru.restaurant.util;

import ru.restaurant.model.*;

import java.util.Objects;

public class EntityUtil {

    private EntityUtil() {
    }

    public static boolean checkVoice(Voice voice, Restaurant restaurant, User user) {
        if (!Objects.isNull(voice) && !Objects.isNull(restaurant) && !Objects.isNull(user)) {
            return checkEntity(voice.getRestaurant(), restaurant) && checkEntity(voice.getUser(), user);
        } else return false;
    }

    public static boolean checkDish(Dish dish, Restaurant restaurant) {
        if (!Objects.isNull(dish) && !Objects.isNull(restaurant)) {
            return checkEntity(dish.getRestaurant(), restaurant);
        } else return false;
    }

    private static boolean checkEntity(NamedEntity first, NamedEntity second) {
        if (!Objects.isNull(first.getId()) && !Objects.isNull(second.getId()) && !Objects.isNull(first.getName()) && !Objects.isNull(second.getName())) {
            return first.getId().equals(second.getId()) && first.getName().equals(second.getName());
        } else return false;
    }
}
