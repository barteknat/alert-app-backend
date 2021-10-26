package com.alert.app.backend.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.dto.StatisticsDto;
import com.alert.app.backend.mapper.StatisticsMapper;
import com.alert.app.backend.repository.StatisticsRepository;
import com.alert.app.backend.status.StatisticsStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper statisticsMapper;

    public List<StatisticsDto> getAll() {
        return statisticsMapper.mapToStatisticsDtoList(statisticsRepository.findAll());
    }

    @Transactional
    public void create(StatisticsStatus status, String remarks) {
        statisticsRepository.save(
                Statistics.builder()
                        .status(status)
                        .date(LocalDateTime.now())
                        .remarks(remarks)
                        .build());
    }
}
