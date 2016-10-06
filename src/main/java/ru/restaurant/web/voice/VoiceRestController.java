package ru.restaurant.web.voice;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.restaurant.model.Voice;
import ru.restaurant.service.VoiceService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@Controller
public class VoiceRestController {

    @Autowired
    VoiceService service;

    public Collection<Voice> getAll() {
        return service.getAllOnDate();
    }

    public Voice get(int id) {
        return service.get(id);
    }

    public Voice create(Voice voice) {
        return service.save(voice, AuthorizedUser.getId());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    public void update(Voice voice, int id) {
        voice.setId(id);
        service.save(voice, AuthorizedUser.getId());
    }
}
