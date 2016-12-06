package ru.restaurant.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.restaurant.TestUtil;
import ru.restaurant.UserTestData;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.UserUtil;
import ru.restaurant.web.AbstractControllerTest;
import org.junit.Test;
import ru.restaurant.web.AbstractControllerTest;
import org.springframework.test.web.servlet.ResultActions;
import ru.restaurant.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;
import static ru.restaurant.TestUtil.userHttpBasic;
import static ru.restaurant.UserTestData.*;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String USER_URL = UserRestController.USER_URL + "/";

    @Autowired
    UserService service;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(USER_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_TO_CLIENT.contentListMatcher(UserUtil.toClient(service.getAll()))));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(USER_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_TO_CLIENT.contentMatcher(UserUtil.toClient(ADMIN)));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(USER_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        User expected = getCreated();
        ResultActions action = mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        UserToClient returnedTo = new UserToClient(returned);
        UserToClient expectedTo = new UserToClient(expected);

        MATCHER_TO_CLIENT.assertEquals(expectedTo, returnedTo);
        MATCHER_TO_CLIENT.assertCollectionEquals(UserUtil.toClient(Arrays.asList(USER, ADMIN_USER, ADMIN, expected)),
                UserUtil.toClient(service.getAll()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(USER_URL + USER_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER_TO_CLIENT.assertCollectionEquals(UserUtil.toClient(Arrays.asList(ADMIN_USER, ADMIN)),
                UserUtil.toClient(service.getAll()));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(USER_URL + 1)
                .with(TestUtil.userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(USER_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(USER_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        mockMvc.perform(put(USER_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, userService.get(USER_ID));
    }

    @Test
    public void testSetRole() throws Exception {
        USER.getRoles().add(Role.ROLE_ADMIN);
        mockMvc.perform(put(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .param("id", "100000")
                .param("role", "ROLE_ADMIN"))
                .andExpect(status().isOk());

        MATCHER.assertEquals(USER, service.get(USER_ID));
    }

    @Test
    public void testDeleteRole() throws Exception {
        ADMIN_USER.getRoles().remove(Role.ROLE_ADMIN);
        mockMvc.perform(delete(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .param("id", "100001")
                .param("role", "ROLE_ADMIN"))
                .andExpect(status().isOk());

        MATCHER.assertEquals(ADMIN_USER, service.get(ADMIN_USER_ID));
        ADMIN_USER.getRoles().add(Role.ROLE_ADMIN);
    }
}