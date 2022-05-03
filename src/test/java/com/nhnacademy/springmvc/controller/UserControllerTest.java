package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

// TODO #3: UserController Test
public class UserControllerTest {
    private MockMvc mockMvc;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userRepository))
                                 .build();
    }

    @Test
    void testUserExists() throws Exception {
        User user = User.create("admin", "12345");
        when(userRepository.getUser(anyString())).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(get("/user/{userId}", "admin"))
            .andExpect(status().isOk())
            .andExpect(view().name("userInfo"))
            .andReturn();

        Optional<User> viewUser = Optional.ofNullable(mvcResult.getModelAndView())
            .map(ModelAndView::getModel)
            .map(m -> m.get("user"))
            .map(User.class::cast);

        assertThat(viewUser).isPresent();
        assertThat(viewUser.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void testUserNotExists() throws Exception {
        when(userRepository.getUser(anyString())).thenReturn(null);

        Throwable th = catchThrowable(() -> mockMvc.perform(get("/user/{userId}", "admin")));

        assertThat(th).isInstanceOf(NestedServletException.class)
                      .hasCauseInstanceOf(UserNotFoundException.class);
    }

}
