package com.alert.app.backend.dto;

import com.alert.app.backend.status.AirQualityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
//    @Enumerated(EnumType.STRING)
    private String levelName;
}
