package com.alert.app.backend.api.gios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiosApiStationDto {

    private long id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    private GiosApiCityDto city;
    private String addressStreet;
}
