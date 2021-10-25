package com.alert.app.backend.service;

import com.alert.app.backend.dto.AirQualityStationDto;
import com.alert.app.backend.mapper.AirQualityStationMapper;
import com.alert.app.backend.repository.AirQualityStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualityStationService {

    private final AirQualityStationMapper airQualityStationMapper;
    private final AirQualityStationRepository airQualityStationRepository;

    public List<AirQualityStationDto> getAll() {
        return airQualityStationMapper.mapToStationDtoList(airQualityStationRepository.findAll());
    }
}
