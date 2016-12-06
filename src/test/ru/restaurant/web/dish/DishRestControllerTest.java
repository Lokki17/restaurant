package ru.restaurant.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.restaurant.DishTestData;
import ru.restaurant.RestaurantTestData;
import ru.restaurant.TestUtil;
import ru.restaurant.UserTestData;
import ru.restaurant.model.Dish;
import ru.restaurant.service.DishService;
import ru.restaurant.web.AbstractControllerTest;
import ru.restaurant.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant.DishTestData.*;
import static ru.restaurant.TestUtil.userHttpBasic;
import static ru.restaurant.UserTestData.ADMIN;
import static ru.restaurant.UserTestData.USER;

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String DISH_URL = DishRestController.DISH_URL + "/";

    @Autowired
    DishService service;

/*    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.MATCHER.contentListMatcher(service.getAll())));
    }*/

    @Test
    public void testCreate() throws Exception {
        Dish expected = getCreated();
        ResultActions action = mockMvc.perform(post(DISH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "100003")
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        expected.setDate(returned.getDate());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9),
                Arrays.asList(service.get(DISH_ID), service.get(DISH_ID + 1), service.get(DISH_ID + 2), service.get(DISH_ID + 3),
                        service.get(DISH_ID + 4), service.get(DISH_ID + 5), service.get(DISH_ID + 6),
                        service.get(DISH_ID + 7), service.get(DISH_ID + 8)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(DISH_URL + DISH_ID)
                .with(userHttpBasic(UserTestData.ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9),
                Arrays.asList(service.get(DISH_ID + 1), service.get(DISH_ID + 2), service.get(DISH_ID + 3),
                        service.get(DISH_ID + 4), service.get(DISH_ID + 5), service.get(DISH_ID + 6),
                        service.get(DISH_ID + 7), service.get(DISH_ID + 8)));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(DISH_URL + 1)
                .with(TestUtil.userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testDeleteUnauth() throws Exception {
        mockMvc.perform(delete(DISH_URL + DISH_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(DISH_URL + DISH_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(DISH_URL + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated))
                .param("restaurantId", "100003"))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, service.get(DISH_ID));
    }
}