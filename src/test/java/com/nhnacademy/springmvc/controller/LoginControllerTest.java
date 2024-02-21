package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LoginControllerTest {
    private MockMvc mockMvc;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userRepository))
                .build();
    }

    @Test
    void testLogin() throws Exception {
        final Cookie cookie = new Cookie("SESSION", "test");

        mockMvc.perform(get("/login").cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("id"))
                .andExpect(view().name("loginSuccess"))
                .andReturn();
    }

    @Test
    void testLoginFail() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andReturn();
    }

    @Test
    void testDoLogin() throws Exception {
        when(userRepository.matches(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("id", "admin")
                        .param("pwd", "12345"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginSuccess"))
                .andReturn();

    }

    @Test
    void testDoLoginFail() throws Exception {
        when(userRepository.matches(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .param("id", "admin")
                        .param("pwd", "12345"))
                .andExpect(view().name("redirect:/login"))
                .andReturn();
    }
}