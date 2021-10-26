package com.alert.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirQualityStationDto {

    private long id;
    private long stationApiId;
    private String name;
    private String street;
    private String city;
}
