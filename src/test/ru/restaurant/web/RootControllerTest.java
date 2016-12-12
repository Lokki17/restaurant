package ru.restaurant.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.restaurant.model.User;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.UserUtil;
import ru.restaurant.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurant.TestUtil.userHttpBasic;
import static ru.restaurant.UserTestData.*;

public class RootControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    public void testRoot() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:/index"));
    }

    @Test
    public void testCreate() throws Exception {
        User expected = getCreatedRoot();
        ResultActions action = mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        UserToClient returnedTo = new UserToClient(returned);
        UserToClient expectedTo = new UserToClient(expected);

        MATCHER_TO_CLIENT.assertEquals(expectedTo, returnedTo);
        MATCHER_TO_CLIENT.assertCollectionEquals(UserUtil.toClient(Arrays.asList(USER_FOR_TEST, ADMIN_USER, ADMIN, expected)),
                UserUtil.toClient(service.getAll()));
    }
}