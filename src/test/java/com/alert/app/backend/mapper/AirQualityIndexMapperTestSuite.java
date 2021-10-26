package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.dto.AirQualityIndexDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirQualityIndexMapperTestSuite {

    @Autowired
    private AirQualityIndexMapper airQualityIndexMapper;

    @Test
    void shouldMapToAirQualityDto() {
        //Given
        AirQualityIndex airQualityIndex = AirQualityIndex.builder()
                .id(1)
                .stationApiId(1)
                .date(LocalDateTime.now())
                .level(1)
                .levelName("test")
                .build();

        //When
        AirQualityIndexDto airQualityIndexDto = airQualityIndexMapper.mapToAirQualityDto(airQualityIndex);

        //Then
        assertThat(airQualityIndexDto.getId()).isEqualTo(airQualityIndex.getId());
        assertThat(airQualityIndexDto.getStationApiId()).isEqualTo(airQualityIndex.getStationApiId());
        assertThat(airQualityIndexDto.getDate()).isEqualTo(airQualityIndex.getDate());
        assertThat(airQualityIndexDto.getLevel()).isEqualTo(airQualityIndex.getLevel());
        assertThat(airQualityIndexDto.getLevelName()).isEqualTo(airQualityIndex.getLevelName());
    }

    @Test
    void shouldMapToAirQualityDtoList() {
        //Given
        List<AirQualityIndex> airQualityIndexList = Arrays.asList(
                AirQualityIndex.builder()
                .id(1)
                .stationApiId(1)
                .date(LocalDateTime.now())
                .level(1)
                .levelName("test")
                .build());

        //When
        List<AirQualityIndexDto> airQualityIndexDtos = airQualityIndexMapper.mapToAirQualityDtoList(airQualityIndexList);

        //Then
        assertEquals(1, airQualityIndexDtos.size());
    }
}
