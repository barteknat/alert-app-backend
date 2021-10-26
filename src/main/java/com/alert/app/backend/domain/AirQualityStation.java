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
@Entity(name = "AIR_QUALITY_STATIONS")
public class AirQualityStation {

    @Id
    @GeneratedValue
    private long id;
    private long stationApiId;
    private String name;
    private String street;
    private String city;
}
