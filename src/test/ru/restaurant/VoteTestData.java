package ru.restaurant;

import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Vote;
import ru.restaurant.to.VoteTo;

import java.time.LocalDate;
import java.util.Objects;

import static ru.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.restaurant.RestaurantTestData.RESTAURANT_2;
import static ru.restaurant.RestaurantTestData.RESTAURANT_4;
import static ru.restaurant.UserTestData.*;
import static ru.restaurant.model.BaseEntity.START_SEQ;

public class VoteTestData {

    public static final Integer VOICE_ID = START_SEQ + 16;

    public static final Vote VOTE_1 = new Vote(VOICE_ID, LocalDate.now(), RESTAURANT_1, ADMIN);
    public static final Vote VOTE_2 = new Vote(VOICE_ID + 1, LocalDate.now(), RESTAURANT_1, ADMIN_USER);
    public static final Vote VOTE_3 = new Vote(VOICE_ID + 2, LocalDate.now(), RESTAURANT_2, USER);

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDate(), actual.getDate()));

    public static final ModelMatcher<VoteTo> MATCHER_TO = ModelMatcher.of(VoteTo.class,
            ((expected, actual) -> expected == actual ||
            Objects.equals(expected.getId(), actual.getId() )
            && Objects.equals(expected.getRestaurant().getName(), actual.getRestaurant().getName())
            && Objects.equals(expected.getDate(), actual.getDate())
            && Objects.equals(expected.getUserId(), actual.getUserId())
            && Objects.equals(expected.getUserName(), actual.getUserName())));

    public static Vote getUpdated(){
        return new Vote(VOICE_ID, LocalDate.now(), RESTAURANT_2, ADMIN);
    }

    public static Vote getCreated(){
        return new Vote(LocalDate.now(), RESTAURANT_4, ADMIN_USER_FOR_TEST);
    }
}
