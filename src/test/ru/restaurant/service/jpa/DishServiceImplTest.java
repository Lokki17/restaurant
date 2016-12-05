package ru.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.restaurant.model.Dish;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.DishService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.restaurant.DishTestData.*;
import static ru.restaurant.UserTestData.USER_ID;

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9),
                Arrays.asList(service.get(DISH_ID + 1), service.get(DISH_ID + 2),
                        service.get(DISH_ID + 3), service.get(DISH_ID + 4), service.get(DISH_ID + 5),
                        service.get(DISH_ID + 6), service.get(DISH_ID + 7), service.get(DISH_ID + 8)));
    }
    @Test
    public void testDeleteNotFound() throws Exception {
        Boolean excepted = service.delete(DISH_ID - 1, USER_ID);
        Assert.isTrue(!excepted);
    }

    @Test
    public void testGetAllOnDate() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9),
                Arrays.asList(service.get(DISH_ID), service.get(DISH_ID + 1), service.get(DISH_ID + 2),
                        service.get(DISH_ID + 3), service.get(DISH_ID + 4), service.get(DISH_ID + 5),
                        service.get(DISH_ID + 6), service.get(DISH_ID + 7), service.get(DISH_ID + 8)));
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, updated.getRestaurant().getId(), USER_ID);
        MATCHER.assertEquals(updated, service.get(DISH_ID));
    }

    @Test
    public void testSave() throws Exception {
        Dish saved = getUpdated();
        service.save(saved, saved.getRestaurant().getId(), USER_ID);
        MATCHER.assertEquals(saved, service.get(DISH_ID));
    }

    @Test
    public void testGet() throws Exception {
        Dish saved = service.get(DISH_ID);
        MATCHER.assertEquals(DISH_1, saved);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        Dish saved = service.get(DISH_ID - 1);
        MATCHER.assertEquals(DISH_1, saved);
    }
}