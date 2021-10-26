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
public class AirQualityIndexDto {

    private long id;
    private long stationApiId;
    private LocalDateTime date;
    private long level;
    private String levelName;
}
