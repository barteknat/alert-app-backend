package com.alert.app.backend.service;

import com.alert.app.backend.domain.Pollution;
import com.alert.app.backend.domain.Sensor;
import com.alert.app.backend.dto.PollutionDto;
import com.alert.app.backend.mapper.PollutionMapper;
import com.alert.app.backend.repository.PollutionRepository;
import com.alert.app.backend.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutionService {

    private final PollutionMapper pollutionMapper;

    private final PollutionRepository pollutionRepository;

    private final SensorRepository sensorRepository;

    public List<PollutionDto> getAll() {
        return pollutionMapper.mapToPollutionDtoList(pollutionRepository.findAll());
    }

    public PollutionDto getById(long id) {
        return pollutionMapper.mapToPollutionDto(pollutionRepository.getById(id));
    }

    public PollutionDto create(long sensorId, PollutionDto pollutionDto) {
        Sensor sensor = sensorRepository.getById(sensorId);
        Pollution pollution = pollutionMapper.mapToPollution(pollutionDto);
        pollution.setSensor(sensor);
        return pollutionMapper.mapToPollutionDto(pollutionRepository.save(pollution));
    }

    public PollutionDto update(PollutionDto pollutionDto) {
        return pollutionMapper.mapToPollutionDto(pollutionRepository.save(pollutionMapper.mapToPollution(pollutionDto)));
    }

    public void delete(long id) {
        pollutionRepository.deleteById(id);
    }
}
