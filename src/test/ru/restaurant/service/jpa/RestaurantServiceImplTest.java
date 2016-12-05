package ru.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.model.Restaurant;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.restaurant.RestaurantTestData.*;

public class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_2, RESTAURANT_3, RESTAURANT_4),
                service.getAll());
    }

    @Test()
    public void testDeleteNotFound() throws Exception {
        Boolean excepted = service.delete(RESTAURANT_ID - 1);
        Assert.isTrue(!excepted);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4),
                service.getAll());
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(RESTAURANT_1, service.get(RESTAURANT_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(RESTAURANT_ID - 1);
    }

    @Test
    public void testGetByName() throws Exception {
        MATCHER.assertEquals(RESTAURANT_1, service.getByName(RESTAURANT_1.getName()));
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RESTAURANT_ID));
    }

    @Test
    public void testSave() throws Exception {
        Restaurant saved = getCreated();
        service.save(saved);
        MATCHER.assertEquals(saved, service.get(saved.getId()));
    }
}