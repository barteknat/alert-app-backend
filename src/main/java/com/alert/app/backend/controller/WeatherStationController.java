package com.alert.app.backend.controller;

import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.service.WeatherStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/weatherStation")
public class WeatherStationController {

    private final WeatherStationService weatherStationService;

    @GetMapping("/all")
    public List<WeatherStationDto> getAll() {
        return weatherStationService.getAll();
    }

    @GetMapping
    public WeatherStationDto getByCity(@RequestParam String city) {
        return weatherStationService.getByCity(city);
    }
}
