package com.alert.app.backend.service;

import com.alert.app.backend.dto.StationDto;
import com.alert.app.backend.mapper.StationMapper;
import com.alert.app.backend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationMapper stationMapper;
    private final StationRepository stationRepository;

    public List<StationDto> getAll() {
        return stationMapper.mapToStationDtoList(stationRepository.findAll());
    }

    public StationDto getById(long id) {
        return stationMapper.mapToStationDto(stationRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        stationRepository.deleteById(id);
    }
}
