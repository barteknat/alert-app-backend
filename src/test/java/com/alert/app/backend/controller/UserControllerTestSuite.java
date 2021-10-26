package com.alert.app.backend.controller;

import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDto getUserDto() {
        return UserDto.builder()
                .id(1)
                .username("test")
                .email("test@test.pl")
                .password("test")
                .build();
    }

    @Test
    void shouldGetAll() throws Exception {
        //Given
        List<UserDto> userDtoList = Arrays.asList(getUserDto());
        when(userService.getAll()).thenReturn(userDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("test")));
    }

    @Test
    void shouldGetByEmail() throws Exception {
        //Given
        when(userService.getByEmail("test@test.pl")).thenReturn(getUserDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user")
                        .queryParam("email", "test@test.pl")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("test")));
    }

    @Test
    void shouldCreate() throws Exception {
        //Given
        when(userService.create(any(UserDto.class))).thenReturn(getUserDto());
        String jsonContent = new Gson().toJson(getUserDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/user")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("test")));
    }

    @Test
    void shouldUpdate() throws Exception {
        //Given
        UserDto userDtoUpdated = UserDto.builder()
                .id(1)
                .username("test updated")
                .email("testtest@test.pl")
                .password("test updated")
                .build();
        when(userService.update(any(UserDto.class))).thenReturn(userDtoUpdated);
        String jsonContent = new Gson().toJson(getUserDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/user")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(userDtoUpdated.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(userDtoUpdated.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is(userDtoUpdated.getPassword())));
    }

    @Test
    void shouldLogIn() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/user/logIn")
                        .queryParam("email", "test@test.pl")
                        .queryParam("password", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldLogOut() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/user/logOut")
                        .queryParam("email", "test@test.pl")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/user/{id}", getUserDto().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
