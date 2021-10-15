package com.alert.app.backend.api.imgw.mapper;

import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.domain.WeatherStation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ImgwMapper {

    public WeatherStation mapToStation(ImgwApiStationDto imgwApiStationDto) {
        return WeatherStation.builder()
                .id(Long.parseLong(Optional.ofNullable(imgwApiStationDto.getId_stacji()).orElse("0")))
                .stationId(Long.parseLong(Optional.ofNullable(imgwApiStationDto.getId_stacji()).orElse("0")))
                .city(imgwApiStationDto.getStacja())
                .date(LocalDate.parse(Optional.ofNullable(imgwApiStationDto.getData_pomiaru()).orElse("1900-01-01")))
                .temperature(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getTemperatura()).orElse("0")))
                .windSpeed(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getPredkosc_wiatru()).orElse("0")))
                .windDirection(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getKierunek_wiatru()).orElse("0")))
                .humidity(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getWilgotnosc_wzgledna()).orElse("0")))
                .rainAmount(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getSuma_opadu()).orElse("0")))
                .pressure(Double.parseDouble(Optional.ofNullable(imgwApiStationDto.getCisnienie()).orElse("0")))
                .build();
    }
}
