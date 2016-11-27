package ru.restaurant.util;

import ru.restaurant.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VoiceUtil {

    public static Map<Restaurant, Integer> getRestaurantVoiceDistribution(Collection<Voice> voices) {
        Map<Restaurant, Integer> destr = new HashMap<>();
        voices.stream()
                .forEach(voice -> destr.merge(voice.getRestaurant(), 1, (a, b) -> a + b));
        return destr;
    }
}
