package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final UserRepository userRepository;

    public MemberController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/members/{memberId}")
    public User getMember(@PathVariable("memberId") String memberId) {
        return userRepository.getUser(memberId);
    }

}
