package com.alert.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherStationDto {

    private long id;
    private long stationId;
    private String city;
    private LocalDate date;
    private double temperature;
    private double windSpeed;
    private double windDirection;
    private double humidity;
    private double rainAmount;
    private double pressure;
}
