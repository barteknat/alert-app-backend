package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualitySensorDto;
import com.alert.app.backend.service.AirQualitySensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sensor")
public class AirQualitySensorController {

    private final AirQualitySensorService airQualitySensorService;

    @GetMapping("/all")
    public List<AirQualitySensorDto> getAll() {
        return airQualitySensorService.getAll();
    }

    @GetMapping
    public List<AirQualitySensorDto> getAllByStationId(@RequestParam long stationId) {
        return airQualitySensorService.getAllByStationId(stationId);
    }
}
