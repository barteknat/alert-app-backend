package com.alert.app.backend.api.facade;

import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.service.ImgwService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiFacade {

    private final ImgwService imgwService;

    private final GiosService giosService;

    public List<ImgwApiStationDto> getAllWeatherStations() {
        return imgwService.getAndSaveAllStations();
    }

    public List<GiosApiStationDto> getAllAirQualityStations() {
        return giosService.getAndSaveAllStations();
    }

    public List<GiosApiSensorDto> getSensorsByStationId(long stationId) {
        return giosService.getAndSaveSensorsByStationId(stationId);
    }

    public GiosApiAirQualityDto getAirQualityIndexByStationId(long stationId) {
        return giosService.getAndSaveAirQualityIndexByStationId(stationId);
    }
}
