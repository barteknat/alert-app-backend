package com.alert.app.backend.service;

import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.service.ImgwService;
import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.mapper.WeatherStationMapper;
import com.alert.app.backend.repository.WeatherStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherStationService {

    private final WeatherStationRepository weatherStationRepository;
    private final WeatherStationMapper weatherStationMapper;

    public List<WeatherStationDto> getAll() {
        return weatherStationMapper.mapToWeatherStationDtoList(weatherStationRepository.findAll());
    }

    public WeatherStationDto getByCity(String city) {
        System.out.println("THIS IS " + weatherStationRepository.getDistinctFirstByCityOrderByIdDesc(city));
        return weatherStationMapper.mapToWeatherStationDto(weatherStationRepository.getDistinctFirstByCityOrderByIdDesc(city));
    }
}
