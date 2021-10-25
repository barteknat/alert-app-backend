package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityStationDto;
import com.alert.app.backend.service.AirQualityStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/station")
public class AirQualityStationController {

    private final AirQualityStationService airQualityStationService;

    @GetMapping
    public List<AirQualityStationDto> getAll() {
        return airQualityStationService.getAll();
    }
}
