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
    private long id;

    private String name;

    private String sulphurDioxide;

    private String nitrogenDioxide;

    private String pM10;

    private String pM2_5;

    private String carbonMonoxide;

    private String benzene;

    private String ozone;

    @ManyToOne
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;
}
