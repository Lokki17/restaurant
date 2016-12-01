package ru.restaurant.web.voice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
import java.net.URI;
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
    @Secured("ROLE_ADMIN")
    public Collection<VoiceTo> get() {
        return VoiceUtil.toToCollection(service.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoiceTo> create(@Valid @RequestBody Voice voice) {
        setRestaurant(voice);
        setUser(voice, AuthorizedUser.getId());
        VoiceTo created = new VoiceTo(service.save(voice, AuthorizedUser.getId()));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/voices" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public boolean delete(@PathVariable("id") Integer id) {
        return service.delete(id, AuthorizedUser.getId());
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
        Assert.notNull(restaurant, "Restaurant not found");
            voice.setRestaurant(restaurant);
    }

    private void setUser(Voice voice, Integer userId){
        User savedUser = userService.get(userId, AuthorizedUser.getId());
        Assert.notNull(savedUser, "User not found");
            voice.setUser(savedUser);
    }
}
