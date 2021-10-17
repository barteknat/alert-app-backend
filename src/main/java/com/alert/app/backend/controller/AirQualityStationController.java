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

    private final AirQualityStationService service;

    @GetMapping
    public List<AirQualityStationDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public AirQualityStationDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
