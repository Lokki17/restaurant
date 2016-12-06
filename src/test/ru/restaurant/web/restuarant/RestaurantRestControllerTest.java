package ru.restaurant.web.restuarant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.TestUtil;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.util.UserUtil;
import ru.restaurant.web.AbstractControllerTest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.TestUtil.userHttpBasic;
import static ru.restaurant.UserTestData.ADMIN;

public class RestaurantRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = RestaurantRestController.RESTAURANT_URL + "/";

    @Autowired
    RestaurantService service;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.MATCHER.contentListMatcher(service.getAll())));
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }
}