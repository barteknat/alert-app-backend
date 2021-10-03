package com.alert.app.backend.api.gios.mapper;

import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiosMapper {

    public Station mapToStation(GiosApiStationDto giosApiStationDto) {
        return Station.builder()
                .id(giosApiStationDto.getId())
                .name(giosApiStationDto.getStationName())
                .street(giosApiStationDto.getAddressStreet())
                .build();
    }

    public City mapToCity(GiosApiStationDto giosApiStationDto) {
        return City.builder()
                .id(giosApiStationDto.getCity().getId())
                .name(giosApiStationDto.getCity().getName())
                .province(giosApiStationDto.getCity().getCommune().getProvinceName())
                .build();
    }

    public Sensor mapToSensor(GiosApiSensorDto giosApiSensorDto) {
        return Sensor.builder()
                .id(giosApiSensorDto.getId())
                .name(giosApiSensorDto.getParam().getParamName())
                .formula(giosApiSensorDto.getParam().getParamFormula())
                .code(giosApiSensorDto.getParam().getParamCode())
                .build();
    }

    public Pollution mapToPollution(GiosApiPollutionDto giosApiPollutionDto) {
        return Pollution.builder()
                .name(giosApiPollutionDto.getKey())
                .build();
    }

    public PollutionValue mapToPollutionValue(GiosApiPollutionValueDto giosApiPollutionValueDto) {
        return PollutionValue.builder()
                .date(giosApiPollutionValueDto.getDate())
                .value(giosApiPollutionValueDto.getValue())
                .build();
    }

    public AirQuality mapToAirQuality(GiosApiAirQualityDto giosApiAirQualityDto) {
        return AirQuality.builder()
                .date(giosApiAirQualityDto.getStCalcDate())
                .level(giosApiAirQualityDto.getStIndexLevel().getId())
                .levelName(giosApiAirQualityDto.getStIndexLevel().getIndexLevelName())
                .build();
    }
}
