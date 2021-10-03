package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityDto;
import com.alert.app.backend.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/airQuality")
public class AirQualityController {

    private final AirQualityService service;

    @GetMapping
    public List<AirQualityDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public AirQualityDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public AirQualityDto create(@RequestParam long stationId, @RequestParam String name, @RequestParam String air) {
        return service.create(stationId, name, air);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public AirQualityDto update(@RequestBody AirQualityDto airQualityDto) {
        return service.update(airQualityDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
