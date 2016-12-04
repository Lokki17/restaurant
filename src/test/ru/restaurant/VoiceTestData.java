package ru.restaurant;

import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Voice;

import java.time.LocalDate;
import java.util.Objects;

import static ru.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.restaurant.RestaurantTestData.RESTAURANT_2;
import static ru.restaurant.model.BaseEntity.START_SEQ;

public class VoiceTestData {

    private static final Integer VOICE_ID = START_SEQ + 16;

    public static final Voice VOICE_1 = new Voice(VOICE_ID, LocalDate.now(), RESTAURANT_1, UserTestData.ADMIN);
    public static final Voice VOICE_2 = new Voice(VOICE_ID + 1, LocalDate.now(), RESTAURANT_1, UserTestData.ADMIN_USER);
    public static final Voice VOICE_3 = new Voice(VOICE_ID + 2, LocalDate.now(), RESTAURANT_2, UserTestData.USER);

    public static final ModelMatcher<Voice> MATCHER = ModelMatcher.of(Voice.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getUser(), actual.getUser())
                            && Objects.equals(expected.getRestaurant(), actual.getRestaurant())
                            && Objects.equals(expected.getDate(), actual.getDate()));
}
