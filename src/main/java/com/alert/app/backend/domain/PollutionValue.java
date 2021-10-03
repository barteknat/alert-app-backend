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
public class PollutionValue {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime date;
    private double value;
    @ManyToOne
    @JoinColumn(name = "POLLUTION_ID")
    private Pollution pollution;
}
