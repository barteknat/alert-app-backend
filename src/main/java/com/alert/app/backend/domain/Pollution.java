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
public class Pollution {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;
}
