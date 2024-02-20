package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(user);
    }

}