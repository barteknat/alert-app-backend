package com.alert.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirQualitySensorDto {

    private long id;
    private long sensorApiId;
    private long stationApiId;
    private String name;
    private String code;
    private LocalDateTime date;
    private double value;
}
