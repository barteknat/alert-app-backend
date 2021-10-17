package com.alert.app.backend.domain;

import com.alert.app.backend.status.AirQualityStatus;
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
@Entity(name = "AIR_QUALITY_INDEXES")
public class AirQualityIndex {

    @Id
    @GeneratedValue
    private long id;
    private long stationApiId;
    private LocalDateTime date;
    private long level;
    private String levelName;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private AirQualityStation airQualityStation;
}
