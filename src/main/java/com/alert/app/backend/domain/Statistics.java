package com.alert.app.backend.domain;

import com.alert.app.backend.status.StatisticsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "STATISTICS")
public class Statistics {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private StatisticsStatus status;
    private String remarks;
}
