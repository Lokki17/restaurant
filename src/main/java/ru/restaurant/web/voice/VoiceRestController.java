package ru.restaurant.web.voice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Voice;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/voices")
public class VoiceRestController {

    @Autowired
    VoiceService service;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Integer> getAll() {
        return service.getAllOnDate();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Voice> get() {
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice create(@RequestBody Voice voice) {
        createVoice(voice, voice.getRestaurant().getId());
        return service.save(voice, AuthorizedUser.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice update(@RequestBody Voice voice, @PathVariable("id") int id) {
        voice.setId(id);
        createVoice(voice, voice.getRestaurant().getId());
        return service.save(voice, AuthorizedUser.getId());
    }

    private void createVoice(Voice voice, Integer restaurantId){
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (restaurant != null){
            voice.setRestaurant(restaurant);
        } else {
            throw new NotFoundException("Not found Restaurant");
        }
    }

}
