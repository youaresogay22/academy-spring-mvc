package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Post;
import com.nhnacademy.springmvc.domain.PostRegisterRequest;
import com.nhnacademy.springmvc.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post/register")
public class PostRegisterController {
    private final PostRepository postRepository;

    public PostRegisterController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String userRegisterForm() {
        return "postRegister";
    }

    // TODO #1: 게시물 등록 처리 구현
    @PostMapping
    public ModelAndView registerPost(@ModelAttribute PostRegisterRequest postRequest) {
        Post post = postRepository.register(postRequest.getTitle(), postRequest.getContent());

        ModelAndView mav = new ModelAndView("postView");
        mav.addObject("post", post);

        return mav;
    }

}
