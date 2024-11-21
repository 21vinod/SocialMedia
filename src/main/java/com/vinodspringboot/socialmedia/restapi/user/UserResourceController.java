package com.vinodspringboot.socialmedia.restapi.user;

import com.vinodspringboot.socialmedia.restapi.exceptionhandling.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public User getUsers(@PathVariable int id) {
        User user = userDaoService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
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
