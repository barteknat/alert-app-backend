package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.dto.AirQualityIndexDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityIndexMapper {

    public AirQualityIndexDto mapToAirQualityDto(AirQualityIndex airQualityIndex) {
        return AirQualityIndexDto.builder()
                .id(airQualityIndex.getId())
                .stationApiId(airQualityIndex.getStationApiId())
                .date(airQualityIndex.getDate())
                .level(airQualityIndex.getLevel())
                .levelName(airQualityIndex.getLevelName())
                .build();
    }

    public List<AirQualityIndexDto> mapToAirQualityDtoList(List<AirQualityIndex> airQualityIndexList) {
        List<AirQualityIndexDto> airQualityIndexDtoList = new ArrayList<>();
        for (AirQualityIndex airQualityIndex : airQualityIndexList) {
            airQualityIndexDtoList.add(mapToAirQualityDto(airQualityIndex));
        }
        return airQualityIndexDtoList;
    }
}
