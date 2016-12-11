package ru.restaurant.util;

import ru.restaurant.model.*;
import ru.restaurant.to.VoiceTo;

import java.util.*;

public class VoiceUtil {

    private VoiceUtil() {
    }

    public static Map<Restaurant, Integer> getRestaurantVoiceDistribution(Collection<Vote> votes) {
        Map<Restaurant, Integer> destr = new TreeMap<>();
        votes.stream()
                .forEach(voice -> destr.merge(voice.getRestaurant(), 1, (a, b) -> a + b));
        return destr;
    }

    public static void checkRestaurant(Vote vote){
        Objects.requireNonNull(vote.getRestaurant());
        Objects.requireNonNull(vote.getRestaurant().getName());
    }

    public static Collection<VoiceTo> toToCollection(Collection<Vote> votes){
        Collection<VoiceTo> result = new LinkedList<>();
        votes.stream().forEach(voice -> result.add(new VoiceTo(voice)));
        return result;
    }
}
