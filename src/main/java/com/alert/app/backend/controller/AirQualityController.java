package com.alert.app.backend.controller;

import com.alert.app.backend.dto.AirQualityDto;
import com.alert.app.backend.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
