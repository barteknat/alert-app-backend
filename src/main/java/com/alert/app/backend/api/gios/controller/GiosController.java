package com.alert.app.backend.api.gios.controller;

import com.alert.app.backend.api.facade.ApiFacade;
import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gios")
public class GiosController {

    private final ApiFacade apiFacade;

    @GetMapping("/stations")
    public List<GiosApiStationDto> getAndSetAllStations() {
        return apiFacade.getAllAirQualityStations();
    }

    @GetMapping("/sensors")
    public List<GiosApiSensorDto> getAndSetSensorsByStationId(@RequestParam long stationId) {
        return apiFacade.getSensorsByStationId(stationId);
    }

    @GetMapping("/airQuality")
    public GiosApiAirQualityDto getAndSetAirQualityByStationId(@RequestParam long stationId) {
        return apiFacade.getAirQualityIndexByStationId(stationId);
    }
}
