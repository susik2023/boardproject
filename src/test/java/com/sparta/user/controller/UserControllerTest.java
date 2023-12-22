package com.sparta.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.boardproject.user.controller.UserController;
import com.sparta.boardproject.user.dto.UserSignUpRequestDto;
import com.sparta.boardproject.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userService;

  @Test
  public void signUp_Success() throws Exception {
    // Given
    String username = "오수식";
    String password = "1234";
    String validatepassword = "1234";
    UserSignUpRequestDto requestDto = new UserSignUpRequestDto(username,password,validatepassword);



    // When
    ResultActions result = mockMvc.perform(post("/api/users/signup")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)));

    // Then
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.username").value("testUser"));
  }

  // Add more test cases as needed...
}