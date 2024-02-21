package com.nhnacademy.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO #3: `@Value` 활용
@Controller
public class ValueTestController {
    private final List<String> list;

    public ValueTestController(List<String> list) {
        this.list = list;
    }

    @GetMapping("/value-test")
    @ResponseBody
    public String valueTest(@Value("${key3}") String key3,
                            @Value("12345") String key4,
                            @Value("#{systemProperties['java.home']}") String javaHome) {
        return String.join(",", list) + "," + key3 + "," + key4 + "\n"
                + javaHome;
        //실행결과: value1,value2,value3,12345, java home path
    }

}
