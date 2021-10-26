package com.alert.app.backend.service;

import com.alert.app.backend.domain.AirQualityStation;
import com.alert.app.backend.dto.AirQualityStationDto;
import com.alert.app.backend.repository.AirQualityStationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AirQualityStationServiceTestSuite {

    @Autowired
    private AirQualityStationService airQualityStationService;

    @Autowired
    private AirQualityStationRepository airQualityStationRepository;

    @Test
    void shouldGetAll() {
        //Given
        AirQualityStation airQualityStation = AirQualityStation.builder()
                .stationApiId(1)
                .city("test")
                .street("test")
                .build();
        long id = airQualityStationRepository.save(airQualityStation).getId();

        //When
        List<AirQualityStationDto> airQualityStationDtoList = airQualityStationService.getAll();

        //Then
        assertEquals(1, airQualityStationDtoList.get(airQualityStationDtoList.size() - 1).getStationApiId());
        assertEquals("test", airQualityStationDtoList.get(airQualityStationDtoList.size() - 1).getCity());
        assertEquals("test", airQualityStationDtoList.get(airQualityStationDtoList.size() - 1).getStreet());

        //CleanUp
        airQualityStationRepository.deleteById(id);
    }
}
