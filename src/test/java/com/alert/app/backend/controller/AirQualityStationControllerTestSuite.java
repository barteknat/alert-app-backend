package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityStationDto;
import com.alert.app.backend.service.AirQualityStationService;
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
@WebMvcTest(AirQualityStationController.class)
class AirQualityStationControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirQualityStationService airQualityStationService;

    @Test
    void shouldGetAll() throws Exception {
        //Given
        List<AirQualityStationDto> airQualityStationDtoList = Arrays.asList(
                AirQualityStationDto.builder()
                        .id(1)
                        .stationApiId(1)
                        .name("test")
                        .street("test")
                        .city("test")
                        .build());
        when(airQualityStationService.getAll()).thenReturn(airQualityStationDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/station")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city", Matchers.is("test")));
    }
}
