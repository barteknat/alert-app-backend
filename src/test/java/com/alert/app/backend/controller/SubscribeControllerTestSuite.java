package com.alert.app.backend.controller;

import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.service.SubscribeService;
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

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(SubscribeController.class)
class SubscribeControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscribeService subscribeService;

    private SubscribeDto getSubscribeDto() {
        return SubscribeDto.builder()
                .id(1)
                .city("test")
                .build();
    }

    @Test
    void shouldGetAll() throws Exception {
        //Given
        List<SubscribeDto> subscribeDtoList = Arrays.asList(getSubscribeDto());
        when(subscribeService.getAll()).thenReturn(subscribeDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/subscribe/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city", Matchers.is("test")));
    }

    @Test
    void shouldGetAllByUserId() throws Exception {
        //Given
        List<SubscribeDto> subscribeDtoList = Arrays.asList(getSubscribeDto());
        when(subscribeService.getAllByUserId(1)).thenReturn(subscribeDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/subscribe/allById")
                        .queryParam("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city", Matchers.is("test")));
    }

    @Test
    void shouldGetByUserIdAndCity() throws Exception {
        //Given
        when(subscribeService.getByUserIdAndCity(1, "test")).thenReturn(getSubscribeDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/subscribe")
                        .queryParam("userId", "1")
                        .queryParam("city", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("test")));
    }

    @Test
    void shouldCreate() throws Exception {
        //Given
        when(subscribeService.create(1, "test")).thenReturn(getSubscribeDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/subscribe")
                        .queryParam("userId", "1")
                        .queryParam("city", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("test")));
    }

    @Test
    void shouldDelete() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/subscribe/{id}", getSubscribeDto().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
