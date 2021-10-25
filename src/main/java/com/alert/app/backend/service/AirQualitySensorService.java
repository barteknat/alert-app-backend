package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.dto.AirQualitySensorDto;
import com.alert.app.backend.mapper.AirQualitySensorMapper;
import com.alert.app.backend.repository.AirQualitySensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualitySensorService {

    private final AirQualitySensorMapper airQualitySensorMapper;
    private final AirQualitySensorRepository airQualitySensorRepository;
    private final GiosService giosService;

    public List<AirQualitySensorDto> getAll() {
        return airQualitySensorMapper.mapToSensorDtoList(airQualitySensorRepository.findAll());
    }

    public List<AirQualitySensorDto> getAllByStationId(long stationId) {
        giosService.getAndSaveSensorsByStationId(stationId);
        return airQualitySensorMapper.mapToSensorDtoList(airQualitySensorRepository.getByStationApiId(stationId));
    }
}
