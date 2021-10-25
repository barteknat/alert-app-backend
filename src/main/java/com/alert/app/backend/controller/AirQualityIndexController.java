package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityIndexDto;
import com.alert.app.backend.service.AirQualityIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/airQuality")
public class AirQualityIndexController {

    private final AirQualityIndexService airQualityIndexService;

    @GetMapping("/all")
    public List<AirQualityIndexDto> getAll() {
        return airQualityIndexService.getAll();
    }

    @GetMapping()
    public AirQualityIndexDto getByStationId(@RequestParam long stationId) {
        return airQualityIndexService.getByStationId(stationId);
    }
}
