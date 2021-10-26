package com.alert.app.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "SUBSCRIBES")
public class Subscribe {

    @Id
    @GeneratedValue
    private long id;
    private String userEmail;
    private String city;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "AIR_QUALITY_STATION_ID")
    private AirQualityStation airQualityStation;
    @ManyToOne
    @JoinColumn(name = "WEATHER_STATION_ID")
    private WeatherStation weatherStation;
}
