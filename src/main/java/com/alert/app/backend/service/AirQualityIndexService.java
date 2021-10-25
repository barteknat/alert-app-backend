package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.service.GiosService;
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
    private final GiosService giosService;

    public List<AirQualityIndexDto> getAll() {
        return airQualityIndexMapper.mapToAirQualityDtoList(airQualityIndexRepository.findAll());
    }

    public AirQualityIndexDto getByStationId(long stationId) {
        giosService.getAndSaveAirQualityIndexByStationId(stationId);
        return airQualityIndexMapper.mapToAirQualityDto(airQualityIndexRepository.getByStationApiId(stationId));
    }

    @Transactional
    public void delete(long id) {
        airQualityIndexRepository.deleteById(id);
    }
}
