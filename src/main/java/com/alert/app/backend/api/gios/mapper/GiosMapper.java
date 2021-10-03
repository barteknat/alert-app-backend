package com.alert.app.backend.api.gios.mapper;

import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.domain.City;
import com.alert.app.backend.domain.Station;
import org.springframework.stereotype.Service;

@Service
public class GiosMapper {

    public Station mapToStation(GiosApiStationDto giosApiStationDto) {
        return Station.builder()
                .id(giosApiStationDto.getId())
                .name(giosApiStationDto.getStationName())
                .build();
    }

    public City mapToCity(GiosApiStationDto giosApiStationDto) {
        return City.builder()
                .id(giosApiStationDto.getCity().getId())
                .name(giosApiStationDto.getCity().getName())
                .build();
    }
}
