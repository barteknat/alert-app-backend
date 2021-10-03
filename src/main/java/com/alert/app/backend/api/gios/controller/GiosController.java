package com.alert.app.backend.api.gios.controller;

import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiPollutionDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.service.GiosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gios")
public class GiosController {

    private final GiosService giosService;

    @GetMapping("/stations")
    public List<GiosApiStationDto> getAllStations() {
        return giosService.getAllStations();
    }

    @GetMapping("/sensors")
    public List<GiosApiSensorDto> getSensorsByStationId(@RequestParam long stationId) {
        return giosService.getSensorsByStationId(stationId);
    }

    @GetMapping("/pollution")
    public GiosApiPollutionDto getPollutionBySensorId(@RequestParam long sensorId) {
        return giosService.getPollutionBySensorId(sensorId);
    }

    @GetMapping("/airQuality")
    public GiosApiAirQualityDto getAirQualityByStationId(@RequestParam long stationId) {
        return giosService.getAirQualityByStationId(stationId);
    }
}
