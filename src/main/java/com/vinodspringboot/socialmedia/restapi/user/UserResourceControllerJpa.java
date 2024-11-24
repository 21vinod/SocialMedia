package com.vinodspringboot.socialmedia.restapi.user;

import com.vinodspringboot.socialmedia.restapi.exceptionhandling.UserNotFoundException;
import com.vinodspringboot.socialmedia.restapi.jpa.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResourceControllerJpa {

    private UsersRepository usersRepository;

    public UserResourceControllerJpa(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getUsers(@PathVariable int id) {
        User user = usersRepository.getById(id);
        if (user.getId()==null) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
        Link link = linkTo(methodOn(this.getClass()).getUsers()).withRel("all-users");

        userEntityModel.add(link);

        return userEntityModel;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = usersRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        usersRepository.deleteById(id);
    }

}
