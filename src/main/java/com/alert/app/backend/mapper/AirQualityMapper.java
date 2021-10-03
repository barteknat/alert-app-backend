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
                .name(airQualityDto.getName())
                .airQualityIndex(airQualityDto.getAirQualityIndex())
                .build();
    }

    public AirQualityDto mapToAirQualityDto(AirQuality airQuality) {
        return AirQualityDto.builder()
                .id(airQuality.getId())
                .name(airQuality.getName())
                .airQualityIndex(airQuality.getAirQualityIndex())
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
