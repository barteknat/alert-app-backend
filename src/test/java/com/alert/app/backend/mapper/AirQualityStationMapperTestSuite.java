package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualityStation;
import com.alert.app.backend.dto.AirQualityStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirQualityStationMapperTestSuite {

    @Autowired
    private AirQualityStationMapper airQualityStationMapper;

    @Test
    void shouldMapToStationDto() {
        //Given
        AirQualityStation airQualityStation = AirQualityStation.builder()
                .id(1)
                .stationApiId(1)
                .name("test")
                .street("test")
                .city("test")
                .build();

        //When
        AirQualityStationDto airQualityStationDto = airQualityStationMapper.mapToStationDto(airQualityStation);

        //Then
        assertEquals(airQualityStation.getId(), airQualityStationDto.getId());
        assertEquals(airQualityStation.getStationApiId(), airQualityStationDto.getStationApiId());
        assertEquals(airQualityStation.getName(), airQualityStationDto.getName());
        assertEquals(airQualityStation.getStreet(), airQualityStationDto.getStreet());
        assertEquals(airQualityStation.getCity(), airQualityStationDto.getCity());
    }

    @Test
    void shouldMapToStationDtoList() {
        //Given
        List<AirQualityStation> airQualityStationList = Arrays.asList(new AirQualityStation());

        //When
        List<AirQualityStationDto> airQualityStationDtoList = airQualityStationMapper.mapToStationDtoList(airQualityStationList);

        //Then
        assertEquals(1, airQualityStationDtoList.size());
    }
}