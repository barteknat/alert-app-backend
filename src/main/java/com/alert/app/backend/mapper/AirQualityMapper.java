package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQuality;
import com.alert.app.backend.dto.AirQualityDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityMapper {

    public AirQuality mapToAirQuality(AirQualityDto airQualityDto) {
        return AirQuality.builder()
                .id(airQualityDto.getId())
                .date(airQualityDto.getDate())
                .level(airQualityDto.getLevel())
                .levelName(airQualityDto.getLevelName())
                .build();
    }

    public AirQualityDto mapToAirQualityDto(AirQuality airQuality) {
        return AirQualityDto.builder()
                .id(airQuality.getId())
                .date(airQuality.getDate())
                .level(airQuality.getLevel())
                .levelName(airQuality.getLevelName())
                .build();
    }

    public List<AirQualityDto> mapToAirQualityDtoList(List<AirQuality> airQualityList) {
        List<AirQualityDto> airQualityDtoList = new ArrayList<>();
        for (AirQuality airQuality : airQualityList) {
            airQualityDtoList.add(mapToAirQualityDto(airQuality));
        }
        return airQualityDtoList;
    }
}
