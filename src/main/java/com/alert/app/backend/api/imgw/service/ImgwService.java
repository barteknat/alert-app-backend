package com.alert.app.backend.api.imgw.service;

import com.alert.app.backend.api.imgw.client.ImgwClient;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.mapper.ImgwMapper;
import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.repository.WeatherStationRepository;
import com.alert.app.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.WEATHER_STATIONS_UPDATED;

@Service
@RequiredArgsConstructor
public class ImgwService {

    private final ImgwClient imgwClient;
    private final ImgwMapper imgwMapper;
    private final WeatherStationRepository weatherStationRepository;
    private final StatisticsService statisticsService;

    @Transactional
    public List<ImgwApiStationDto> getAndSaveAllStations() {
        List<ImgwApiStationDto> imgwApiStationDtoList = imgwClient.getImgwStations();
        for (ImgwApiStationDto imgwApiStationDto : imgwApiStationDtoList) {
            long stationId = 0;
            try {
                stationId = weatherStationRepository.getByCity(imgwApiStationDto.getStacja()).getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (weatherStationRepository.existsById(stationId)) {
                weatherStationRepository.deleteById(stationId);
            }
            WeatherStation weatherStation = imgwMapper.mapToStation(imgwApiStationDto);
            weatherStationRepository.save(weatherStation);
        }
        statisticsService.create(Statistics.builder()
                .status(WEATHER_STATIONS_UPDATED)
                .remarks("")
                .build());
        return imgwApiStationDtoList;
    }
}
