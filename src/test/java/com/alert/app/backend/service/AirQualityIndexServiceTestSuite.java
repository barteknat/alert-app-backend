package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.dto.AirQualityIndexDto;
import com.alert.app.backend.mapper.AirQualityIndexMapper;
import com.alert.app.backend.repository.AirQualityIndexRepository;
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
class AirQualityIndexServiceTestSuite {

    @InjectMocks
    private AirQualityIndexService airQualityIndexService;

    @Mock
    private AirQualityIndexMapper airQualityIndexMapper;

    @Mock
    private AirQualityIndexRepository airQualityIndexRepository;

    @Mock
    private GiosService giosService;

    private AirQualityIndex getAirQualityIndex() {
        return AirQualityIndex.builder()
                .stationApiId(1)
                .level(1)
                .levelName("test")
                .build();
    }

    private AirQualityIndexDto getAirQualityIndexDto() {
        return AirQualityIndexDto.builder()
                .stationApiId(1)
                .level(1)
                .levelName("test")
                .build();
    }

    @Test
    void shouldGetAll() {
        //Given
        List<AirQualityIndex> airQualityIndexList = Arrays.asList(getAirQualityIndex());
        List<AirQualityIndexDto> airQualityIndexDtoList = Arrays.asList(getAirQualityIndexDto());
        when(airQualityIndexRepository.findAll()).thenReturn(airQualityIndexList);
        when(airQualityIndexMapper.mapToAirQualityDtoList(airQualityIndexList)).thenReturn(airQualityIndexDtoList);

        //When
        List<AirQualityIndexDto> airQualityIndexDtos = airQualityIndexService.getAll();

        //Then
        assertEquals(1, airQualityIndexDtos.get(airQualityIndexDtos.size() - 1).getStationApiId());
        assertEquals(1, airQualityIndexDtos.get(airQualityIndexDtos.size() - 1).getLevel());
        assertEquals("test", airQualityIndexDtos.get(airQualityIndexDtos.size() - 1).getLevelName());
    }

    @Test
    void shouldGetByStationId() {
        //Given
        when(giosService.getAndSaveAirQualityIndexByStationId(1)).thenReturn(new GiosApiAirQualityDto());
        when(airQualityIndexRepository.getByStationApiId(1)).thenReturn(getAirQualityIndex());
        when(airQualityIndexMapper.mapToAirQualityDto(getAirQualityIndex())).thenReturn(getAirQualityIndexDto());

        //When
        AirQualityIndexDto airQualityIndexDto = airQualityIndexService.getByStationId(1);

        //Then
        assertEquals(1, airQualityIndexDto.getStationApiId());
        assertEquals(1, airQualityIndexDto.getLevel());
        assertEquals("test", airQualityIndexDto.getLevelName());
    }
}
