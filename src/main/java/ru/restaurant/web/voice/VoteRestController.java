package ru.restaurant.web.voice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant.model.Restaurant;
import ru.restaurant.model.Vote;
import ru.restaurant.service.RestaurantService;
import ru.restaurant.service.UserService;
import ru.restaurant.service.VoiceService;
import ru.restaurant.to.VoiceTo;
import ru.restaurant.util.VoiceUtil;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(VoteRestController.VOICE_URL)
public class VoteRestController {
    public static final String VOICE_URL = "/voices";

    @Autowired
    private VoiceService service;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Integer> getAll() {
        return service.getAllOnDate();
    }

    //only for testing
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Collection<VoiceTo> get() {
        return VoiceUtil.toToCollection(service.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoiceTo> create(@Valid @RequestBody Vote vote) {
        VoiceTo created = new VoiceTo(service.save(vote, AuthorizedUser.getId()));
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
    public VoiceTo update(@Valid @RequestBody Vote vote, @PathVariable("id") int id) {
        vote.setId(id);
        return new VoiceTo(service.save(vote, AuthorizedUser.getId()));
    }
}
