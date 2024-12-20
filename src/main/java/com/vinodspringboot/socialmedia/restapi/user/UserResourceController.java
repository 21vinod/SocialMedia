package com.vinodspringboot.socialmedia.restapi.user;

import com.vinodspringboot.socialmedia.restapi.exceptionhandling.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResourceController {

    private UserDaoService userDaoService;

    public UserResourceController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userDaoService.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUsers(@PathVariable int id) throws NoSuchMethodException {
        User user = userDaoService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
       // Link link = linkTo(methodOn(this.getClass(),getUsers())).withRel("all-user");
//        Link link = linkTo(methodOn()).withRel("all-user");

        Link link = linkTo(methodOn(this.getClass()).getUsers()).withRel("all-users");

        userEntityModel.add(link);

        return userEntityModel;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userDaoService.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        userDaoService.deleteUserById(id);
    }

}
