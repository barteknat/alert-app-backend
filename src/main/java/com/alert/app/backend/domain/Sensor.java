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
@Entity
public class Sensor {

    @Id
    private long id;
    private String name;
    private String formula;
    private String code;
    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;
}
