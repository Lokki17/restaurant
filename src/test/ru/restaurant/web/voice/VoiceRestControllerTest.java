package ru.restaurant.web.voice;

import org.junit.Test;
import ru.restaurant.model.Voice;
import ru.restaurant.service.VoiceService;
import ru.restaurant.to.VoiceTo;
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
import static ru.restaurant.VoiceTestData.*;

public class VoiceRestControllerTest extends AbstractControllerTest {

    private static final String VOICE_URL = VoiceRestController.VOICE_URL + "/";

    @Autowired
    VoiceService service;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(VOICE_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(VOICE_URL + ADMIN)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        MATCHER.assertCollectionEquals(Arrays.asList(VOICE_1, VOICE_2, VOICE_3), service.getAll());
    }

    @Test
    public void testCreate() throws Exception {
        Voice created = getCreated();
        VoiceTo createdTo = new VoiceTo(created);
        ResultActions action = mockMvc.perform(post(VOICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        VoiceTo returned = MATCHER_TO.fromJsonAction(action);
        createdTo.setId(returned.getId());

        MATCHER_TO.assertEquals(createdTo, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(VOICE_URL + VOICE_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(VOICE_2, VOICE_3), service.getAll());
    }

    @Test
    public void testDeleteUnauth() throws Exception {
        mockMvc.perform(delete(VOICE_URL + VOICE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteWithoutRight() throws Exception {
        mockMvc.perform(delete(VOICE_URL + VOICE_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        Voice updated = getUpdated();
        VoiceTo updatedTo = new VoiceTo(updated);

        mockMvc.perform(put(VOICE_URL + VOICE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        Voice saved = service.get(ADMIN_ID);
        MATCHER_TO.assertEquals(updatedTo, new VoiceTo(saved));
    }
}