package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualitySensorDto;
import com.alert.app.backend.service.AirQualitySensorService;
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
@WebMvcTest(AirQualitySensorController.class)
class AirQualitySensorControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirQualitySensorService airQualitySensorService;

    private AirQualitySensorDto getAirQualitySensorDto() {
        return AirQualitySensorDto.builder()
                .id(1)
                .stationApiId(1)
                .sensorApiId(1)
                .name("test")
                .code("test")
                .value(1.0)
                .build();
    }

    @Test
    void shouldGetAll() throws Exception {
        List<AirQualitySensorDto> airQualitySensorDtoList = Arrays.asList(getAirQualitySensorDto());
        when(airQualitySensorService.getAll()).thenReturn(airQualitySensorDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/sensor/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sensorApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value", Matchers.is(1.0)));
    }

    @Test
    void shouldGetAllByStationId() throws Exception {
        //Given
        List<AirQualitySensorDto> airQualitySensorDtoList = Arrays.asList(getAirQualitySensorDto());
        when(airQualitySensorService.getAllByStationId(1)).thenReturn(airQualitySensorDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/sensor")
                        .queryParam("stationId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sensorApiId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value", Matchers.is(1.0)));
    }
}
