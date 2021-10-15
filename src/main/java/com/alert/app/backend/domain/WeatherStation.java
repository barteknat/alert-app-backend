package com.alert.app.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "WEATHER_STATIONS")
public class WeatherStation {

    @Id
    @GeneratedValue
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
