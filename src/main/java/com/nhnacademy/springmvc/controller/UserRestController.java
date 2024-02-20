package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userRepository.addUser(user.getId(), user.getPassword(), user.getAge(), user.getName());
        return ResponseEntity.created(URI.create("users")).body("user " + user.toString() + " created");
    }

}