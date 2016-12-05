package ru.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.UserTestData;
import ru.restaurant.VoiceTestData;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.restaurant.RestaurantTestData.*;
import static ru.restaurant.UserTestData.*;
import static ru.restaurant.VoiceTestData.*;
import static ru.restaurant.VoiceTestData.MATCHER;
import static ru.restaurant.VoiceTestData.getCreated;

public class VoiceServiceImplTest extends AbstractServiceTest{

    @Autowired
    VoiceService service;

    @Test
    public void testGet() throws Exception {
        Voice saved = service.get(ADMIN_ID);
        VoiceTestData.MATCHER.assertEquals(VOICE_1, saved);
        UserTestData.MATCHER.assertEquals(VOICE_1.getUser(), saved.getUser());
        RestaurantTestData.MATCHER.assertEquals(VOICE_1.getRestaurant(), saved.getRestaurant());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(USER_ID - 1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Voice> all = service.getAll();
        Assert.isTrue(all.size() == 3);
        MATCHER.assertCollectionEquals(Arrays.asList(VOICE_1, VOICE_2, VOICE_3), all);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(VOICE_ID, ADMIN_ID);
        Assert.isTrue(service.getAll().size() == 2);
    }

    @Test
    public void testGetAllOnDate() throws Exception {
        Map<Restaurant, Integer> saved = service.getAllOnDate();
        Assert.isTrue(saved.get(RESTAURANT_1) == 2);
        Assert.isTrue(saved.get(RESTAURANT_2) == 1);
    }

    @Test
    public void testSave() throws Exception {
        Voice created = getCreated();
        Voice actual = service.save(created, ADMIN_ID);
        VoiceTestData.MATCHER.assertEquals(created, actual);
        UserTestData.MATCHER.assertEquals(created.getUser(), actual.getUser());
        RestaurantTestData.MATCHER.assertEquals(created.getRestaurant(), actual.getRestaurant());
    }
}