package ru.restaurant.util;

import ru.restaurant.model.*;
import ru.restaurant.to.VoteTo;

import java.util.*;

public class VoteUtil {

    private VoteUtil() {
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

    public static Collection<VoteTo> toToCollection(Collection<Vote> votes){
        Collection<VoteTo> result = new LinkedList<>();
        votes.stream().forEach(voice -> result.add(new VoteTo(voice)));
        return result;
    }
}
