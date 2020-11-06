package com.alura.forum.controllers;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SneakyThrows
  @Test
  public void shouldReturnBadRequestWhenDataAuthenticationIncorrect() {
    URI uri = new URI("/auth");
    String jsonRequest = "{\"email\": \"invalid@gmail.com\", \"password\": \"2sqer432\"}";
    this.mockMvc.perform(MockMvcRequestBuilders
      .post(uri).content(jsonRequest)
      .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
  }
}
