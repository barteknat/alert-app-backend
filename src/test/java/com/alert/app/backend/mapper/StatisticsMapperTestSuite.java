package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.dto.StatisticsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.LOGIN_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatisticsMapperTestSuite {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Test
    void shouldMapToStatisticsDto() {
        //Given
        Statistics statistics = Statistics.builder()
                .id(1)
                .status(LOGIN_SUCCESS)
                .date(LocalDateTime.now())
                .remarks("test")
                .build();
        //When
        StatisticsDto statisticsDto = statisticsMapper.mapToStatisticsDto(statistics);

        //Then
        assertEquals(statistics.getId(), statisticsDto.getId());
        assertEquals(statistics.getStatus(), statisticsDto.getStatus());
        assertEquals(statistics.getDate(), statisticsDto.getDate());
        assertEquals(statistics.getRemarks(), statisticsDto.getRemarks());
    }

    @Test
    void shouldMapToStatisticsDtoList() {
        //Given
        List<Statistics> statisticsList = Arrays.asList(new Statistics());

        //When
        List<StatisticsDto> statisticsDtoList = statisticsMapper.mapToStatisticsDtoList(statisticsList);

        //Then
        assertEquals(1, statisticsDtoList.size());
    }
}