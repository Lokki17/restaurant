package ru.restaurant.util;

import ru.restaurant.model.*;
import ru.restaurant.to.VoiceTo;
import ru.restaurant.util.exception.NotFoundException;

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

    public static void checkRestaurant(Voice voice){
        Objects.requireNonNull(voice.getRestaurant());
        Objects.requireNonNull(voice.getRestaurant().getId());
    }

    public static Collection<VoiceTo> toToCollection(Collection<Voice> voices){
        Collection<VoiceTo> result = new LinkedList<>();
        voices.stream().forEach(voice -> result.add(new VoiceTo(voice)));
        return result;
    }

    public Voice checkVoice(Voice result, String message) {
        if (result == null){
            throw new NotFoundException(message);
        }
        return result;
    }
}
