package ru.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.UserTestData;
import ru.restaurant.VoteTestData;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Vote;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.VoiceService;
import ru.restaurant.to.VoiceTo;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static ru.restaurant.RestaurantTestData.*;
import static ru.restaurant.UserTestData.*;
import static ru.restaurant.VoteTestData.*;
import static ru.restaurant.VoteTestData.MATCHER;
import static ru.restaurant.VoteTestData.getCreated;

public class VoteServiceImplTest extends AbstractServiceTest{

    @Autowired
    VoiceService service;

    @Test
    public void testGet() throws Exception {
        Vote saved = service.get(ADMIN_ID);
        VoteTestData.MATCHER.assertEquals(VOTE_1, saved);
        UserTestData.MATCHER.assertEquals(VOTE_1.getUser(), saved.getUser());
        RestaurantTestData.MATCHER.assertEquals(VOTE_1.getRestaurant(), saved.getRestaurant());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(USER_ID - 1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Vote> all = service.getAll();
        Assert.isTrue(all.size() == 3);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE_1, VOTE_2, VOTE_3), all);
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
    @Transactional
    public void testSave() throws Exception {
        Vote created = getCreated();
        Vote actual = service.save(created, ADMIN_ID);
        created.setId(actual.getId());
        VoteTestData.MATCHER_TO.assertEquals(new VoiceTo(created), new VoiceTo(actual));
    }
}