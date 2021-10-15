package com.alert.app.backend.api.imgw.service;

import com.alert.app.backend.api.imgw.client.ImgwClient;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.mapper.ImgwMapper;
import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.repository.WeatherStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgwService {

    private final ImgwClient imgwClient;
    private final ImgwMapper imgwMapper;
    private final WeatherStationRepository weatherStationRepository;

    public List<ImgwApiStationDto> getAllStations() {
        List<ImgwApiStationDto> imgwApiStationDtoList = imgwClient.getImgwStations();
        for (ImgwApiStationDto imgwApiStationDto : imgwApiStationDtoList) {
            if (weatherStationRepository.existsByCity(imgwApiStationDto.getStacja())) {
                weatherStationRepository.deleteByCity(imgwApiStationDto.getStacja());
            }
            WeatherStation weatherStation = imgwMapper.mapToStation(imgwApiStationDto);
            weatherStationRepository.save(weatherStation);
        }
        return imgwApiStationDtoList;
    }
}
