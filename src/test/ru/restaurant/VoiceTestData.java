package ru.restaurant;

import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Voice;
import ru.restaurant.to.VoiceTo;

import java.time.LocalDate;
import java.util.Objects;

import static ru.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.restaurant.RestaurantTestData.RESTAURANT_2;
import static ru.restaurant.UserTestData.*;
import static ru.restaurant.model.BaseEntity.START_SEQ;

public class VoiceTestData {

    public static final Integer VOICE_ID = START_SEQ + 16;

    public static final Voice VOICE_1 = new Voice(VOICE_ID, LocalDate.now(), RESTAURANT_1, ADMIN);
    public static final Voice VOICE_2 = new Voice(VOICE_ID + 1, LocalDate.now(), RESTAURANT_1, ADMIN_USER);
    public static final Voice VOICE_3 = new Voice(VOICE_ID + 2, LocalDate.now(), RESTAURANT_2, USER);

    public static final ModelMatcher<Voice> MATCHER = ModelMatcher.of(Voice.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDate(), actual.getDate()));

    public static final ModelMatcher<VoiceTo> MATCHER_TO = ModelMatcher.of(VoiceTo.class,
            ((expected, actual) -> expected == actual ||
            Objects.equals(expected.getId(), actual.getId() )
            && Objects.equals(expected.getRestaurant().getName(), actual.getRestaurant().getName())
            && Objects.equals(expected.getDate(), actual.getDate())
            && Objects.equals(expected.getUserId(), actual.getUserId())
            && Objects.equals(expected.getUserName(), actual.getUserName())));

    public static Voice getUpdated(){
        return new Voice(VOICE_ID, LocalDate.now(), RESTAURANT_2, ADMIN);
    }

    public static Voice getCreated(){
        return new Voice(LocalDate.now(), RESTAURANT_2, ADMIN);
    }
}
