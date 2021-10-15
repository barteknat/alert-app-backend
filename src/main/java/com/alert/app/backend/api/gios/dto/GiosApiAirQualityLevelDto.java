package com.alert.app.backend.api.gios.dto;

import com.alert.app.backend.status.AirQualityStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiosApiAirQualityLevelDto {

    private long id;
//    @Enumerated(EnumType.STRING)
    private String indexLevelName;
}
