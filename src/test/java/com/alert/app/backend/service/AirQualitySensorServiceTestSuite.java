package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.domain.AirQualitySensor;
import com.alert.app.backend.dto.AirQualitySensorDto;
import com.alert.app.backend.mapper.AirQualitySensorMapper;
import com.alert.app.backend.repository.AirQualitySensorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirQualitySensorServiceTestSuite {

    @InjectMocks
    private AirQualitySensorService airQualitySensorService;

    @Mock
    private AirQualitySensorMapper airQualitySensorMapper;

    @Mock
    private AirQualitySensorRepository airQualitySensorRepository;

    @Mock
    private GiosService giosService;

    private List<AirQualitySensor> getAirQualitySensorList() {
        return Arrays.asList(AirQualitySensor.builder()
                .stationApiId(1)
                .code("test")
                .name("test")
                .build());
    }

    private List<AirQualitySensorDto> getAirQualitySensorDtoList() {
        return Arrays.asList(AirQualitySensorDto.builder()
                .stationApiId(1)
                .code("test")
                .name("test")
                .build());
    }

    @Test
    void shouldGetAll() {
        //Given
        when(airQualitySensorRepository.findAll()).thenReturn(getAirQualitySensorList());
        when(airQualitySensorMapper.mapToSensorDtoList(getAirQualitySensorList())).thenReturn(getAirQualitySensorDtoList());

        //When
        List<AirQualitySensorDto> airQualitySensorDtoList = airQualitySensorService.getAll();

        //Then
        assertEquals(1, airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getStationApiId());
        assertEquals("test", airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getCode());
        assertEquals("test", airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getName());
    }

    @Test
    void shouldGetAllByStationId() {
        //Given
        when(giosService.getAndSaveSensorsByStationId(1)).thenReturn(Arrays.asList(new GiosApiSensorDto()));
        when(airQualitySensorRepository.getByStationApiId(1)).thenReturn(getAirQualitySensorList());
        when(airQualitySensorMapper.mapToSensorDtoList(getAirQualitySensorList())).thenReturn(getAirQualitySensorDtoList());

        //When
        List<AirQualitySensorDto> airQualitySensorDtoList = airQualitySensorService.getAllByStationId(1);

        //Then
        assertEquals(1, airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getStationApiId());
        assertEquals("test", airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getCode());
        assertEquals("test", airQualitySensorDtoList.get(airQualitySensorDtoList.size() - 1).getName());
    }
}
