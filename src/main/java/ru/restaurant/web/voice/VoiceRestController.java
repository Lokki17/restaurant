package ru.restaurant.web.voice;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.Voice;
import ru.restaurant.service.VoiceService;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;

@Controller
public class VoiceRestController {

    @Autowired
    VoiceService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Voice> getAll() {
        return service.getAllOnDate();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice create(Voice voice) {
        return service.save(voice, AuthorizedUser.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Voice voice, @PathVariable("id") int id) {
        voice.setId(id);
        service.save(voice, AuthorizedUser.getId());
    }
}
