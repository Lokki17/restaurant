package ru.restaurant.util;

import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VoiceUtil {

    public static Map<Restaurant, Integer> getRestaurantVoiceDistribution(Collection<Voice> voices) {
        Map<Restaurant, Integer> destr = new HashMap<>();
        voices.stream()
                .forEach(voice -> destr.merge(voice.getDish().getRestaurant(), 1, (a, b) -> a + b));
        return destr;
    }
}
