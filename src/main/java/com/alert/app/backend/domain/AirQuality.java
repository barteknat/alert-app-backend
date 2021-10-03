package com.alert.app.backend.domain;

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
@Entity
public class AirQuality {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime date;
    private long level;
    private String levelName;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
