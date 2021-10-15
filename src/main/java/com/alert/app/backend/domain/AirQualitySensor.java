package com.alert.app.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "AIR_QUALITY_SENSORS")
public class AirQualitySensor {

    @Id
    @GeneratedValue
    private long id;
    private long sensorApiId;
    private long stationApiId;
    private String name;
    private String code;
    private LocalDateTime date;
    private double value;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private AirQualityStation airQualityStation;
}
