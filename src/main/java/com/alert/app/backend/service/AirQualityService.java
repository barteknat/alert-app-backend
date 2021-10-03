package com.alert.app.backend.service;

import com.alert.app.backend.dto.AirQualityDto;
import com.alert.app.backend.mapper.AirQualityMapper;
import com.alert.app.backend.repository.AirQualityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final AirQualityMapper airQualityMapper;
    private final AirQualityRepository airQualityRepository;

    public List<AirQualityDto> getAll() {
        return airQualityMapper.mapToAirQualityDtoList(airQualityRepository.findAll());
    }

    public AirQualityDto getById(long id) {
        return airQualityMapper.mapToAirQualityDto(airQualityRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        airQualityRepository.deleteById(id);
    }
}
