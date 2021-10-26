package com.alert.app.backend.controller;

import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.service.WeatherStationService;
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
@WebMvcTest(WeatherStationController.class)
class WeatherStationControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherStationService weatherStationService;

    private WeatherStationDto getWeatherStationDto() {
        return WeatherStationDto.builder()
                .id(1)
                .stationId(1)
                .city("test")
                .temperature(1.0)
                .windSpeed(1.0)
                .windDirection(1.0)
                .humidity(1.0)
                .rainAmount(1.0)
                .pressure(1.0)
                .build();
    }

    @Test
    void shouldGetAll() throws Exception {
        //Given
        List<WeatherStationDto> weatherStationDtoList = Arrays.asList(getWeatherStationDto());
        when(weatherStationService.getAll()).thenReturn(weatherStationDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weatherStation/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperature", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].windSpeed", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].windDirection", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].humidity", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rainAmount", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pressure", Matchers.is(1.0)));
    }

    @Test
    void shouldGetByCity() throws Exception {
        //Given
        when(weatherStationService.getByCity("test")).thenReturn(getWeatherStationDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weatherStation")
                        .queryParam("city", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stationId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windDirection", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rainAmount", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure", Matchers.is(1.0)));
    }
}
