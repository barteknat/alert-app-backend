package com.alert.app.backend.api.gios.mapper;

import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.domain.*;
import org.springframework.stereotype.Service;

@Service
public class GiosMapper {

    public AirQualityStation mapToStation(GiosApiStationDto giosApiStationDto) {
        return AirQualityStation.builder()
                .stationApiId(giosApiStationDto.getId())
                .name(giosApiStationDto.getStationName())
                .street(giosApiStationDto.getAddressStreet())
                .city(giosApiStationDto.getCity().getName())
                .build();
    }

    public AirQualitySensor mapToSensor(GiosApiSensorDto giosApiSensorDto, GiosApiPollutionValueDto giosApiPollutionValueDto) {
        return AirQualitySensor.builder()
                .sensorApiId(giosApiSensorDto.getId())
                .stationApiId(giosApiSensorDto.getStationId())
                .name(giosApiSensorDto.getParam().getParamName())
                .code(giosApiSensorDto.getParam().getParamCode())
                .date(giosApiPollutionValueDto.getDate())
                .value(giosApiPollutionValueDto.getValue())
                .build();
    }

    public AirQualityIndex mapToAirQuality(GiosApiAirQualityDto giosApiAirQualityDto, long stationId) {
        return AirQualityIndex.builder()
                .stationApiId(stationId)
                .date(giosApiAirQualityDto.getStCalcDate())
                .level(giosApiAirQualityDto.getStIndexLevel().getId())
                .levelName(giosApiAirQualityDto.getStIndexLevel().getIndexLevelName())
                .build();
    }
}
