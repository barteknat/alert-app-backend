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
public class Station {

    @Id
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;
}
