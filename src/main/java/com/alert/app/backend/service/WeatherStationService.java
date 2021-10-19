package com.alert.app.backend.service;

import com.alert.app.backend.api.imgw.service.ImgwService;
import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.mapper.WeatherStationMapper;
import com.alert.app.backend.repository.WeatherStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherStationService {

    private final WeatherStationRepository weatherStationRepository;
    private final WeatherStationMapper weatherStationMapper;
    private final ImgwService imgwService;

    public WeatherStationDto getByCity(String city) {
        imgwService.getAndSaveAllStations();
        return weatherStationMapper.mapToWeatherStationDto(weatherStationRepository.getDistinctFirstByCityOrderByIdDesc(city));
    }
}
