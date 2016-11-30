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
import ru.restaurant.to.VoiceTo;
import ru.restaurant.util.VoiceUtil;
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
    public Collection<VoiceTo> get() {
        return VoiceUtil.toToCollection(service.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoiceTo create(@Valid @RequestBody Voice voice) {
        setRestaurant(voice);
        setUser(voice, AuthorizedUser.getId());
        return new VoiceTo(service.save(voice, AuthorizedUser.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id, AuthorizedUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoiceTo update(@Valid @RequestBody Voice voice, @PathVariable("id") int id) {
        voice.setId(id);
        setRestaurant(voice);
        setUser(voice, AuthorizedUser.getId());
        return new VoiceTo(service.save(voice, AuthorizedUser.getId()));
    }

    private void setRestaurant(Voice voice){
        VoiceUtil.checkId(voice);
        Restaurant restaurant = restaurantService.getByName(voice.getRestaurant().getName());
//        Restaurant restaurant = restaurantService.get(voice.getRestaurant().getId());
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
