package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityIndexDto;
import com.alert.app.backend.service.AirQualityIndexService;
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
@WebMvcTest(AirQualityIndexController.class)
class AirQualityIndexControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirQualityIndexService airQualityIndexService;

    private AirQualityIndexDto getAirQualityIndexDto() {
        return AirQualityIndexDto.builder()
                .id(1)
                .stationApiId(1)
                .level(1)
                .levelName("test")
                .build();
    }

    @Test
    void shouldGetAll() throws Exception {
        //Given
        List<AirQualityIndexDto> airQualityIndexDtoList = Arrays.asList(getAirQualityIndexDto());
        when(airQualityIndexService.getAll()).thenReturn(airQualityIndexDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/airQuality/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].level", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].levelName", Matchers.is("test")));
    }

    @Test
    void shouldGetByStationId() throws Exception {
        //Given
        when(airQualityIndexService.getByStationId(1)).thenReturn(getAirQualityIndexDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/airQuality")
                        .queryParam("stationId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stationApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.levelName", Matchers.is("test")));
    }
}
