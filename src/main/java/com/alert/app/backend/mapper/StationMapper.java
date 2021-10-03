package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Station;
import com.alert.app.backend.dto.StationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationMapper {

    public StationDto mapToStationDto(Station station) {
        return StationDto.builder()
                .id(station.getId())
                .name(station.getName())
                .build();
    }

    public List<StationDto> mapToStationDtoList(List<Station> stationList) {
        List<StationDto> stationDtoList = new ArrayList<>();
        for (Station station : stationList) {
            stationDtoList.add(mapToStationDto(station));
        }
        return stationDtoList;
    }
}
