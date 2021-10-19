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

    @GetMapping
    public List<AirQualitySensorDto> getAllByStationId(@RequestParam long stationId) {
        return airQualitySensorService.getAllByStationId(stationId);
    }

//    @GetMapping(value = "/{id}")
//    public AirQualitySensorDto getById(@PathVariable long id) {
//        return service.getById(id);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable long id) {
//        service.delete(id);
//    }
}
