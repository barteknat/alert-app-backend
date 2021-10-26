package com.alert.app.backend.api.imgw.controller;

import com.alert.app.backend.api.facade.ApiFacade;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
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
@WebMvcTest(ImgwController.class)
class ImgwControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiFacade apiFacade;

    @Test
    void shouldGetAndSetAllStations() throws Exception {
        //Given
        List<ImgwApiStationDto> imgwApiStationDtoList = Arrays.asList(
                ImgwApiStationDto.builder()
                        .id_stacji("1")
                        .stacja("test")
                        .temperatura("1")
                        .predkosc_wiatru("1")
                        .kierunek_wiatru("1")
                        .wilgotnosc_wzgledna("1")
                        .suma_opadu("1")
                        .cisnienie("1")
                        .build());
        when(apiFacade.getAllWeatherStations()).thenReturn(imgwApiStationDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/imgw/stations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id_stacji", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stacja", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperatura", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].predkosc_wiatru", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kierunek_wiatru", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].wilgotnosc_wzgledna", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].suma_opadu", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cisnienie", Matchers.is("1")));
    }
}
