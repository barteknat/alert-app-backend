package com.alert.app.backend.service;

import com.alert.app.backend.domain.AirQuality;
import com.alert.app.backend.domain.Station;
import com.alert.app.backend.dto.AirQualityDto;
import com.alert.app.backend.mapper.AirQualityMapper;
import com.alert.app.backend.repository.AirQualityRepository;
import com.alert.app.backend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final AirQualityMapper airQualityMapper;

    private final AirQualityRepository airQualityRepository;

    private final StationRepository stationRepository;

    public List<AirQualityDto> getAll() {
        return airQualityMapper.mapToAirQualityDtoList(airQualityRepository.findAll());
    }

    public AirQualityDto getById(long id) {
        return airQualityMapper.mapToAirQualityDto(airQualityRepository.getById(id));
    }

    public AirQualityDto create(long stationId, String name, String air) {
        Station station = stationRepository.getById(stationId);
        AirQuality airQuality = new AirQuality();
        airQuality.setStation(station);
        airQuality.setName(name);
        airQuality.setAirQualityIndex(air);
        return airQualityMapper.mapToAirQualityDto(airQualityRepository.save(airQuality));
    }

    public AirQualityDto update(AirQualityDto airQualityDto) {
        return airQualityMapper.mapToAirQualityDto(airQualityRepository.save(airQualityMapper.mapToAirQuality(airQualityDto)));
    }

    public void delete(long id) {
        airQualityRepository.deleteById(id);
    }
}
