package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO #6: thymeleaf view를 사용하는 controller
@Controller
public class ThymeleafController {
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("greeting", "Hello");
        return "thymeleaf/hello";
    }

}
