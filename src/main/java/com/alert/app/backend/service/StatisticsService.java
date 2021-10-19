package com.alert.app.backend.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.repository.StatisticsRepository;
import com.alert.app.backend.status.StatisticsStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Statistics create(StatisticsStatus status, String remarks) {
        return statisticsRepository.save(
                Statistics.builder()
                .status(status)
                .remarks(remarks)
                .build());
    }
}
