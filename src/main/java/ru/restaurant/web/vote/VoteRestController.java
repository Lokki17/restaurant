package ru.restaurant.web.vote;


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
import ru.restaurant.service.VoteService;
import ru.restaurant.to.VoteTo;
import ru.restaurant.util.VoteUtil;
import ru.restaurant.web.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(VoteRestController.RESTAURANT_URL)
public class VoteRestController {
    public static final String RESTAURANT_URL = "/restaurants";
    public static final String VOTE_URL = "/votes";

    @Autowired
    private VoteService service;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Integer> getAll() {
        return service.getAllOnDate();
    }

    //only for testing
    @GetMapping(value = "/votes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Collection<VoteTo> get() {
        return VoteUtil.toToCollection(service.getAll());
    }

    @PostMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> create(@Valid @RequestBody Vote vote, @PathVariable("restaurantId") Integer restaurantId) {
        VoteTo created = new VoteTo(service.save(vote, AuthorizedUser.getId(), restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/" + restaurantId + "/votes/" + created.getId())
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

/*    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public boolean delete(@PathVariable("id") Integer id) {
        return service.delete(id, AuthorizedUser.getId());
    }*/

    @PutMapping(value = "/{restaurantId}/votes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo update(@Valid @RequestBody Vote vote, @PathVariable("id") int id, @PathVariable("restaurantId") Integer restaurantId) {
        vote.setId(id);
        return new VoteTo(service.save(vote, AuthorizedUser.getId(), restaurantId));
    }
}
