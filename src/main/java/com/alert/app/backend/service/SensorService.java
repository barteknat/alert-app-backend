package com.alert.app.backend.service;

import com.alert.app.backend.domain.Sensor;
import com.alert.app.backend.domain.Station;
import com.alert.app.backend.dto.SensorDto;
import com.alert.app.backend.mapper.SensorMapper;
import com.alert.app.backend.repository.SensorRepository;
import com.alert.app.backend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorMapper sensorMapper;

    private final SensorRepository sensorRepository;

    private final StationRepository stationRepository;

    public List<SensorDto> getAll() {
        return sensorMapper.mapToSensorDtoList(sensorRepository.findAll());
    }

    public SensorDto getById(long id) {
        return sensorMapper.mapToSensorDto(sensorRepository.getById(id));
    }

    public SensorDto create(long stationId, String name) {
        Station station = stationRepository.getById(stationId);
        Sensor sensor = new Sensor();
        sensor.setStation(station);
        sensor.setName(name);
        return sensorMapper.mapToSensorDto(sensorRepository.save(sensor));
    }

    public void delete(long id) {
        sensorRepository.deleteById(id);
    }
}
