package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Sensor;
import com.alert.app.backend.dto.SensorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorMapper {

    public Sensor mapToSensor(SensorDto sensorDto) {
        return Sensor.builder()
                .id(sensorDto.getId())
                .name(sensorDto.getName())
                .build();
    }

    public SensorDto mapToSensorDto(Sensor sensor) {
        return SensorDto.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .build();
    }

    public List<SensorDto> mapToSensorDtoList(List<Sensor> sensorList) {
        List<SensorDto> sensorDtoList = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            sensorDtoList.add(mapToSensorDto(sensor));
        }
        return sensorDtoList;
    }
}
