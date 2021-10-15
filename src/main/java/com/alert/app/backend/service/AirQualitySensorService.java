package com.alert.app.backend.service;

import com.alert.app.backend.dto.AirQualitySensorDto;
import com.alert.app.backend.mapper.AirQualitySensorMapper;
import com.alert.app.backend.repository.AirQualitySensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualitySensorService {

    private final AirQualitySensorMapper airQualitySensorMapper;
    private final AirQualitySensorRepository airQualitySensorRepository;

    public List<AirQualitySensorDto> getAll() {
        return airQualitySensorMapper.mapToSensorDtoList(airQualitySensorRepository.findAll());
    }

    public AirQualitySensorDto getById(long id) {
        return airQualitySensorMapper.mapToSensorDto(airQualitySensorRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        airQualitySensorRepository.deleteById(id);
    }
}
