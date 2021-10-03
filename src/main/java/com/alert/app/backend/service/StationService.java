package com.alert.app.backend.service;

import com.alert.app.backend.domain.City;
import com.alert.app.backend.domain.Station;
import com.alert.app.backend.dto.StationDto;
import com.alert.app.backend.mapper.StationMapper;
import com.alert.app.backend.repository.CityRepository;
import com.alert.app.backend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationMapper stationMapper;

    private final StationRepository stationRepository;

    private final CityRepository cityRepository;

    public List<StationDto> getAll() {
        return stationMapper.mapToStationDtoList(stationRepository.findAll());
    }

    public StationDto getById(long id) {
        return stationMapper.mapToStationDto(stationRepository.getById(id));
    }

    public StationDto create(long cityId, String name) {
        City city = cityRepository.getById(cityId);
        Station station = new Station();
        station.setCity(city);
        station.setName(name);
        return stationMapper.mapToStationDto(stationRepository.save(station));
    }

    public void delete(long id) {
        stationRepository.deleteById(id);
    }
}
