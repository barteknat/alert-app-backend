package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualityStation;
import com.alert.app.backend.dto.AirQualityStationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityStationMapper {

    public AirQualityStationDto mapToStationDto(AirQualityStation airQualityStation) {
        return AirQualityStationDto.builder()
                .id(airQualityStation.getId())
                .stationApiId(airQualityStation.getStationApiId())
                .name(airQualityStation.getName())
                .street(airQualityStation.getStreet())
                .city(airQualityStation.getCity())
                .build();
    }

    public List<AirQualityStationDto> mapToStationDtoList(List<AirQualityStation> airQualityStationList) {
        List<AirQualityStationDto> airQualityStationDtoList = new ArrayList<>();
        for (AirQualityStation airQualityStation : airQualityStationList) {
            airQualityStationDtoList.add(mapToStationDto(airQualityStation));
        }
        return airQualityStationDtoList;
    }
}
