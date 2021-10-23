package com.alert.app.backend.controller;

import com.alert.app.backend.dto.StatisticsDto;
import com.alert.app.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/all")
    public List<StatisticsDto> getAll() {
        return statisticsService.getAll();
    }
}
