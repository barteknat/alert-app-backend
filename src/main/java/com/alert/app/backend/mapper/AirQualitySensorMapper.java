package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.AirQualitySensor;
import com.alert.app.backend.dto.AirQualitySensorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualitySensorMapper {

    public AirQualitySensorDto mapToSensorDto(AirQualitySensor airQualitySensor) {
        return AirQualitySensorDto.builder()
                .id(airQualitySensor.getId())
                .stationApiId(airQualitySensor.getStationApiId())
                .sensorApiId(airQualitySensor.getSensorApiId())
                .name(airQualitySensor.getName())
                .code(airQualitySensor.getCode())
                .value(airQualitySensor.getValue())
                .date(airQualitySensor.getDate())
                .build();
    }

    public List<AirQualitySensorDto> mapToSensorDtoList(List<AirQualitySensor> airQualitySensorList) {
        List<AirQualitySensorDto> airQualitySensorDtoList = new ArrayList<>();
        for (AirQualitySensor airQualitySensor : airQualitySensorList) {
            airQualitySensorDtoList.add(mapToSensorDto(airQualitySensor));
        }
        return airQualitySensorDtoList;
    }
}
