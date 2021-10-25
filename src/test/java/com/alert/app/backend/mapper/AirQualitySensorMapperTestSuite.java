package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualitySensor;
import com.alert.app.backend.dto.AirQualitySensorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirQualitySensorMapperTestSuite {

    @Autowired
    private AirQualitySensorMapper airQualitySensorMapper;

    @Test
    void shouldMapToSensorDto() {
        //Given
        AirQualitySensor airQualitySensor = AirQualitySensor.builder()
                .id(1)
                .stationApiId(1)
                .sensorApiId(1)
                .name("test")
                .code("test")
                .value(1)
                .date(LocalDateTime.now())
                .build();

        //When
        AirQualitySensorDto airQualitySensorDto = airQualitySensorMapper.mapToSensorDto(airQualitySensor);

        //Then
        assertEquals(airQualitySensor.getId(), airQualitySensorDto.getId());
        assertEquals(airQualitySensor.getStationApiId(), airQualitySensorDto.getStationApiId());
        assertEquals(airQualitySensor.getSensorApiId(), airQualitySensorDto.getSensorApiId());
        assertEquals(airQualitySensor.getName(), airQualitySensorDto.getName());
        assertEquals(airQualitySensor.getCode(), airQualitySensorDto.getCode());
        assertEquals(airQualitySensor.getValue(), airQualitySensorDto.getValue());
        assertEquals(airQualitySensor.getDate(), airQualitySensorDto.getDate());
    }

    @Test
    void shouldMapToSensorDtoList() {
        //Given
        List<AirQualitySensor> airQualitySensorList = Arrays.asList(new AirQualitySensor());

        //When
        List<AirQualitySensorDto> airQualitySensorDtoList = airQualitySensorMapper.mapToSensorDtoList(airQualitySensorList);

        //Then
        assertEquals(1, airQualitySensorDtoList.size());
    }
}