package com.alert.app.backend.api.gios.controller;

import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.exception.UpdateException;
import com.alert.app.backend.repository.WeatherStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gios")
public class GiosController {

    private final GiosService giosService;
    private final WeatherStationRepository weatherStationRepository;

    @GetMapping("/stations")
    public List<GiosApiStationDto> getAndSaveAllStations() throws UpdateException {
        return giosService.getAndSaveAllStations();
    }

    @GetMapping("/sensors")
    public List<GiosApiSensorDto> getAndSaveSensorsByStationId(@RequestParam long stationId) {
        return giosService.getAndSaveSensorsByStationId(stationId);
    }

    @GetMapping("/airQuality")
    public GiosApiAirQualityDto getAndSaveAirQualityByStationId(@RequestParam long stationId) {
        return giosService.getAndSaveAirQualityIndexByStationId(stationId);
    }
}
