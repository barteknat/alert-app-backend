package com.alert.app.backend.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public void create(Statistics statistics) {
        statisticsRepository.save(statistics);
    }
}
