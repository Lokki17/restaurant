package ru.restaurant.web.voice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.User;
import ru.restaurant.model.Voice;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.service.UserService;
import ru.restaurant.service.VoiceService;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/voices")
public class VoiceRestController {

    @Autowired
    VoiceService service;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Integer> getAll() {
        return service.getAllOnDate();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Voice> get() {
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice create(@Valid @RequestBody Voice voice) {
        setVoice(voice, voice.getRestaurant().getId());
        return service.save(voice, AuthorizedUser.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Voice update(@Valid @RequestBody Voice voice, @PathVariable("id") int id) {
        voice.setId(id);
        setVoice(voice, voice.getRestaurant().getId());
        setUser(voice, voice.getUser().getId());
        return service.save(voice, AuthorizedUser.getId());
    }

    private void setVoice(Voice voice, Integer restaurantId){
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (restaurant != null){
            voice.setRestaurant(restaurant);
        } else {
            throw new NotFoundException("Not found Restaurant");
        }
    }

    private void setUser(Voice voice, Integer userId){
        User savedUser = userService.get(userId, AuthorizedUser.getId());
        if (savedUser != null){
            voice.setUser(savedUser);
        } else {
            throw new NotFoundException("Not found User");
        }
    }

}
