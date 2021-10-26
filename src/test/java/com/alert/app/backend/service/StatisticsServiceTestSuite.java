package com.alert.app.backend.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.dto.StatisticsDto;
import com.alert.app.backend.repository.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.LOGIN_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatisticsServiceTestSuite {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    void shouldGetAll() {
        //Given
        Statistics statistics = Statistics.builder()
                .status(LOGIN_SUCCESS)
                .remarks("test")
                .build();
        long id = statisticsRepository.save(statistics).getId();

        //When
        List<StatisticsDto> statisticsDtoList = statisticsService.getAll();

        //Then
        assertEquals(LOGIN_SUCCESS, statisticsDtoList.get(statisticsDtoList.size() - 1).getStatus());
        assertEquals("test", statisticsDtoList.get(statisticsDtoList.size() - 1).getRemarks());

        //CleanUp
        statisticsRepository.deleteById(id);
    }

    @Test
    void shouldCreate() {
        //Given
        long statisticsBeforeCreate = statisticsRepository.findAll().size();

        //When
        statisticsService.create(LOGIN_SUCCESS, "test");

        //Then
        long statisticsAfterCreate = statisticsRepository.findAll().size();
        assertNotEquals(statisticsBeforeCreate, statisticsAfterCreate);

        //CleanUp
        for (Statistics statistics : statisticsRepository.findAll()) {
            if (statistics.getRemarks().equals("test")) {
                statisticsRepository.deleteById(statistics.getId());
            }
        }
    }
}
