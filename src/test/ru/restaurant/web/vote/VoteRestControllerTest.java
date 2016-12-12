package ru.restaurant.web.vote;

import org.junit.Test;
import ru.restaurant.model.Vote;
import ru.restaurant.service.VoteService;
import ru.restaurant.to.VoteTo;
import ru.restaurant.web.AbstractControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import ru.restaurant.web.json.JsonUtil;

import java.util.Arrays;

import static ru.restaurant.TestUtil.userHttpBasic;
import static ru.restaurant.UserTestData.ADMIN;
import static ru.restaurant.UserTestData.ADMIN_ID;
import static ru.restaurant.UserTestData.USER;
import static ru.restaurant.VoteTestData.*;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String VOTE_URL = VoteRestController.VOTE_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(VOTE_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(VOTE_URL + ADMIN)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE_1, VOTE_2, VOTE_3), service.getAll());
    }

    @Test
    public void testCreate() throws Exception {
        Vote created = getCreated();
        VoteTo createdTo = new VoteTo(created);
        ResultActions action = mockMvc.perform(post(VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        VoteTo returned = MATCHER_TO.fromJsonAction(action);
        createdTo.setId(returned.getId());

        MATCHER_TO.assertEquals(createdTo, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(VOTE_URL + VOICE_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE_2, VOTE_3), service.getAll());
    }

    @Test
    public void testDeleteUnauth() throws Exception {
        mockMvc.perform(delete(VOTE_URL + VOICE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteWithoutRight() throws Exception {
        mockMvc.perform(delete(VOTE_URL + VOICE_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        Vote updated = getUpdated();
        VoteTo updatedTo = new VoteTo(updated);

        mockMvc.perform(put(VOTE_URL + VOICE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        Vote saved = service.get(ADMIN_ID);
        MATCHER_TO.assertEquals(updatedTo, new VoteTo(saved));
    }
}