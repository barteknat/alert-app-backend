package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.dto.StatisticsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsMapper {

    public StatisticsDto mapToStatisticsDto(Statistics statistics) {
        return StatisticsDto.builder()
                .id(statistics.getId())
                .status(statistics.getStatus())
                .date(statistics.getDate())
                .remarks(statistics.getRemarks())
                .build();
    }

    public List<StatisticsDto> mapToStatisticsDtoList (List<Statistics> statisticsList) {
        List<StatisticsDto> statisticsDtoList = new ArrayList<>();
        for (Statistics statistics : statisticsList) {
            statisticsDtoList.add(mapToStatisticsDto(statistics));
        }
        return statisticsDtoList;
    }
}
