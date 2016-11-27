package ru.restaurant.util.exception;

import ru.restaurant.model.NamedEntity;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;

import java.util.Objects;

public class EntityUtil {

    public static boolean checkEntity(Voice voice, Restaurant restaurant, User user) {
        if (!Objects.isNull(voice) && !Objects.isNull(restaurant) && !Objects.isNull(user)) {
            return checkEntity(voice.getRestaurant(), restaurant) && checkEntity(voice.getUser(), user);
        } else return false;
    }

    private static boolean checkEntity(NamedEntity first, NamedEntity second) {
        if (!Objects.isNull(first.getId()) && !Objects.isNull(second.getId()) && !Objects.isNull(first.getName()) && !Objects.isNull(second.getName())) {
            return first.getId().equals(second.getId()) && first.getName().equals(second.getName());
        } else return false;
    }
}
