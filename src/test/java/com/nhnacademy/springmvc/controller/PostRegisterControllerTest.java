package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.PostRepository;
import com.nhnacademy.springmvc.validator.PostRegisterRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

// TODO #4: PostRegisterController Test
public class PostRegisterControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        PostRepository postRepository = mock(PostRepository.class);
        PostRegisterRequestValidator validator = new PostRegisterRequestValidator();
        PostRegisterController controller = new PostRegisterController(postRepository, validator);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                                 .setValidator(validator)
                                 .build();
    }

    @Test
    void testValidation() throws Exception {
        Throwable th = catchThrowable(() ->
            mockMvc.perform(post("/post/register")
                    .param("title", "")
                    .param("content", ""))
                .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
            .hasCauseInstanceOf(ValidationFailedException.class);
    }

}
