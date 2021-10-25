package com.alert.app.backend.api.gios.mapper;

import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.domain.AirQualitySensor;
import com.alert.app.backend.domain.AirQualityStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiosMapperTestSuite {

    @Autowired
    private GiosMapper giosMapper;

    @Test
    void shouldMapToStation() {
        //Given
        GiosApiCommuneDto giosApiCommuneDto = GiosApiCommuneDto.builder()
                .communeName("test")
                .districtName("test")
                .provinceName("test")
                .build();

        GiosApiCityDto giosApiCityDto = GiosApiCityDto.builder()
                .id(1)
                .name("test")
                .commune(giosApiCommuneDto)
                .build();

        GiosApiStationDto giosApiStationDto = GiosApiStationDto.builder()
                .id(1)
                .stationName("test")
                .addressStreet("test")
                .city(giosApiCityDto)
                .build();

        //When
        AirQualityStation airQualityStation = giosMapper.mapToStation(giosApiStationDto);

        //Then
        assertEquals(giosApiStationDto.getId(), airQualityStation.getStationApiId());
        assertEquals(giosApiStationDto.getStationName(), airQualityStation.getName());
        assertEquals(giosApiStationDto.getAddressStreet(), airQualityStation.getStreet());
        assertEquals(giosApiStationDto.getCity().getName(), airQualityStation.getCity());
    }

    @Test
    void shouldMapToSensor() {
        //Given
        GiosApiSensorParamDto giosApiSensorParamDto = GiosApiSensorParamDto.builder()
                .paramName("test")
                .paramFormula("test")
                .paramCode("test")
                .idParam(1)
                .build();

        GiosApiPollutionValueDto giosApiPollutionValueDto = GiosApiPollutionValueDto.builder()
                .date(LocalDateTime.now())
                .value(1)
                .build();

        GiosApiSensorDto giosApiSensorDto = GiosApiSensorDto.builder()
                .id(1)
                .stationId(1)
                .param(giosApiSensorParamDto)
                .param(giosApiSensorParamDto)
                .build();

        //When
        AirQualitySensor airQualitySensor = giosMapper.mapToSensor(giosApiSensorDto, giosApiPollutionValueDto);

        //Then
        assertEquals(giosApiSensorDto.getId(), airQualitySensor.getSensorApiId());
        assertEquals(giosApiSensorDto.getStationId(), airQualitySensor.getStationApiId());
        assertEquals(giosApiSensorDto.getParam().getParamName(), airQualitySensor.getName());
        assertEquals(giosApiSensorDto.getParam().getParamCode(), airQualitySensor.getCode());
        assertEquals(giosApiPollutionValueDto.getDate(), airQualitySensor.getDate());
        assertEquals(giosApiPollutionValueDto.getValue(), airQualitySensor.getValue());
    }

    @Test
    void shouldMapToAirQuality() {
        //Given
        GiosApiAirQualityLevelDto giosApiAirQualityLevelDto = GiosApiAirQualityLevelDto.builder()
                .id(1)
                .indexLevelName("test")
                .build();

        GiosApiAirQualityDto giosApiAirQualityDto = GiosApiAirQualityDto.builder()
                .stCalcDate(LocalDateTime.now())
                .stIndexLevel(giosApiAirQualityLevelDto)
                .build();

        //When
        AirQualityIndex airQualityIndex = giosMapper.mapToAirQuality(giosApiAirQualityDto, 1);

        //Then
        assertEquals(1, airQualityIndex.getStationApiId());
        assertEquals(giosApiAirQualityDto.getStCalcDate(), airQualityIndex.getDate());
        assertEquals(giosApiAirQualityLevelDto.getId(), airQualityIndex.getLevel());
        assertEquals(giosApiAirQualityLevelDto.getIndexLevelName(), airQualityIndex.getLevelName());
    }
}
