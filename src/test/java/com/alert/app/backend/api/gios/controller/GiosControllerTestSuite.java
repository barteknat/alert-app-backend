package com.alert.app.backend.api.gios.controller;

import com.alert.app.backend.api.facade.ApiFacade;
import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
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
@WebMvcTest(GiosController.class)
class GiosControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiFacade apiFacade;

    @Test
    void shouldGetAndSetAllStations() throws Exception {
        //Given
        List<GiosApiStationDto> giosApiStationDtoList = Arrays.asList(
                GiosApiStationDto.builder()
                        .id(1)
                        .stationName("test")
                        .addressStreet("test")
                        .build());
        when(apiFacade.getAllAirQualityStations()).thenReturn(giosApiStationDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gios/stations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationName", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressStreet", Matchers.is("test")));
    }

    @Test
    void shouldGetAndSetSensorsByStationId() throws Exception {
        //Given
        List<GiosApiSensorDto> giosApiSensorDtoList = Arrays.asList(
                GiosApiSensorDto.builder()
                        .id(1)
                        .stationId(1)
                        .build());
        when(apiFacade.getSensorsByStationId(1)).thenReturn(giosApiSensorDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gios/sensors")
                        .queryParam("stationId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stationId", Matchers.is(1)));
    }

    @Test
    void shouldGetAndSetAirQualityByStationId() throws Exception {
        //Given
        GiosApiAirQualityDto giosApiAirQualityDto = new GiosApiAirQualityDto();
        when(apiFacade.getAirQualityIndexByStationId(1)).thenReturn(giosApiAirQualityDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gios/airQuality")
                        .queryParam("stationId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
    }
}
