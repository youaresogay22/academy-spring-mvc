package com.nhnacademy.springmvc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// TODO #2: Java Config 파일
@Configuration
@PropertySource("classpath:some.properties")
public class PropertiesConfig {
    @Value("${key1}")
    //@Value("${key1}") <- key값으로 하고싶은 경우
    private String key1;

    @Bean
    public List<String> keys(@Value("${key2}") String key2) {
        List<String> list = new ArrayList<>();
        list.add(key1);
        list.add(key2);

        return list;
    }

}
