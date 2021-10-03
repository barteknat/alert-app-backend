package com.alert.app.backend.service;

import com.alert.app.backend.dto.SensorDto;
import com.alert.app.backend.mapper.SensorMapper;
import com.alert.app.backend.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorMapper sensorMapper;
    private final SensorRepository sensorRepository;

    public List<SensorDto> getAll() {
        return sensorMapper.mapToSensorDtoList(sensorRepository.findAll());
    }

    public SensorDto getById(long id) {
        return sensorMapper.mapToSensorDto(sensorRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        sensorRepository.deleteById(id);
    }
}
