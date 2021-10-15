package com.alert.app.backend.api.gios.service;

import com.alert.app.backend.api.gios.client.GiosClient;
import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.api.gios.mapper.GiosMapper;
import com.alert.app.backend.domain.*;
import com.alert.app.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiosService {

    private final GiosClient giosClient;
    private final GiosMapper giosMapper;
    private final AirQualityStationRepository airQualityStationRepository;
    private final AirQualitySensorRepository airQualitySensorRepository;
    private final AirQualityIndexRepository airQualityIndexRepository;

    public List<GiosApiStationDto> getAllStations() {
        List<GiosApiStationDto> giosApiStationDtoList = giosClient.getGiosStations();
        for (GiosApiStationDto giosApiStationDto : giosApiStationDtoList) {
            AirQualityStation airQualityStation = giosMapper.mapToStation(giosApiStationDto);
            if (!airQualityStationRepository.existsByCity(giosApiStationDto.getCity().getName())) {
                airQualityStationRepository.save(airQualityStation);
            }
        }
        return giosApiStationDtoList;
    }

    public List<GiosApiSensorDto> getSensorsByStationId(long stationId) {
        List<GiosApiSensorDto> giosApiSensorDtoList = giosClient.getGiosSensors(stationId);
        for (GiosApiSensorDto giosApiSensorDto : giosApiSensorDtoList) {
            AirQualityStation airQualityStation = airQualityStationRepository.getByStationApiId(stationId);
            GiosApiPollutionDto giosApiPollutionDto = giosClient.getGiosPollution(giosApiSensorDto.getId());
            for (GiosApiPollutionValueDto giosApiPollutionValueDto : giosApiPollutionDto.getValues()) {
                if (giosApiPollutionValueDto.getValue() != 0) {
                    AirQualitySensor airQualitySensor = giosMapper.mapToSensor(giosApiSensorDto, giosApiPollutionValueDto);
                    airQualitySensor.setAirQualityStation(airQualityStation);
                    airQualitySensorRepository.save(airQualitySensor);
                    break;
                }
            }
        }
        return giosApiSensorDtoList;
    }

    public GiosApiAirQualityDto getAirQualityIndexByStationId(long stationId) {
        GiosApiAirQualityDto giosApiAirQualityDto = giosClient.getGiosAirQuality(stationId);
        AirQualityStation airQualityStation = airQualityStationRepository.getByStationApiId(stationId);
        AirQualityIndex airQualityIndex = giosMapper.mapToAirQuality(giosApiAirQualityDto, stationId);
        airQualityIndex.setAirQualityStation(airQualityStation);
        airQualityIndexRepository.save(airQualityIndex);
        return giosApiAirQualityDto;
    }
}
