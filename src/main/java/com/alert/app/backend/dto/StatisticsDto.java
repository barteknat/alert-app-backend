package com.alert.app.backend.dto;

import com.alert.app.backend.status.StatisticsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticsDto {

    private long id;
    private StatisticsStatus status;
    private LocalDateTime date;
    private String remarks;
}
