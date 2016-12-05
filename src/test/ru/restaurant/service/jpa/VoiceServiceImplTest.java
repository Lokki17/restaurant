package ru.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.UserTestData;
import ru.restaurant.VoiceTestData;
import ru.restaurant.model.Voice;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.restaurant.UserTestData.ADMIN_ID;
import static ru.restaurant.UserTestData.USER;
import static ru.restaurant.UserTestData.USER_ID;
import static ru.restaurant.VoiceTestData.MATCHER;
import static ru.restaurant.VoiceTestData.VOICE_1;
import static ru.restaurant.VoiceTestData.VOICE_ID;

public class VoiceServiceImplTest extends AbstractServiceTest{

    @Autowired
    VoiceService service;

    @Test
    public void testGet() throws Exception {
        Voice saved = service.get(ADMIN_ID);
        MATCHER.assertEquals(VOICE_1, saved);
        UserTestData.MATCHER.assertEquals(VOICE_1.getUser(), saved.getUser());
        RestaurantTestData.MATCHER.assertEquals(VOICE_1.getRestaurant(), saved.getRestaurant());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(USER_ID - 1);
    }

    @Test
    public void testGetAll() throws Exception {
        Assert.isTrue(service.getAll().size() == 3);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(VOICE_ID, ADMIN_ID);
        Assert.isTrue(service.getAll().size() == 2);
    }

    @Test
    public void testGetAllOnDate() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }
}