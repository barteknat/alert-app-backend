package com.alert.app.backend.service;

import com.alert.app.backend.dto.AirQualityIndexDto;
import com.alert.app.backend.mapper.AirQualityIndexMapper;
import com.alert.app.backend.repository.AirQualityIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualityIndexService {

    private final AirQualityIndexMapper airQualityIndexMapper;
    private final AirQualityIndexRepository airQualityIndexRepository;

    public List<AirQualityIndexDto> getAll() {
        return airQualityIndexMapper.mapToAirQualityDtoList(airQualityIndexRepository.findAll());
    }

    public AirQualityIndexDto getById(long id) {
        return airQualityIndexMapper.mapToAirQualityDto(airQualityIndexRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        airQualityIndexRepository.deleteById(id);
    }
}
