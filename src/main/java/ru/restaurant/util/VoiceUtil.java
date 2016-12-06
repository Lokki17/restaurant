package ru.restaurant.util;

import ru.restaurant.model.*;
import ru.restaurant.to.VoiceTo;

import java.util.*;

public class VoiceUtil {

    private VoiceUtil() {
    }

    public static Map<Restaurant, Integer> getRestaurantVoiceDistribution(Collection<Voice> voices) {
        Map<Restaurant, Integer> destr = new TreeMap<>();
        voices.stream()
                .forEach(voice -> destr.merge(voice.getRestaurant(), 1, (a, b) -> a + b));
        return destr;
    }

    public static void checkId(Voice voice){
        Objects.requireNonNull(voice.getRestaurant());
    }

    public static Collection<VoiceTo> toToCollection(Collection<Voice> voices){
        Collection<VoiceTo> result = new LinkedList<>();
        voices.stream().forEach(voice -> result.add(new VoiceTo(voice)));
        return result;
    }
}
