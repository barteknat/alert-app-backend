package com.alert.app.backend.api.gios.service;

import com.alert.app.backend.api.gios.client.GiosClient;
import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.api.gios.mapper.GiosMapper;
import com.alert.app.backend.domain.*;
import com.alert.app.backend.repository.*;
import com.alert.app.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.AIR_QUALITY_STATIONS_UPDATED;

@Service
@RequiredArgsConstructor
public class GiosService {

    private final GiosClient giosClient;
    private final GiosMapper giosMapper;
    private final AirQualityStationRepository airQualityStationRepository;
    private final AirQualitySensorRepository airQualitySensorRepository;
    private final AirQualityIndexRepository airQualityIndexRepository;
    private final StatisticsService statisticsService;

    @Transactional
    public List<GiosApiStationDto> getAndSaveAllStations() {
        List<GiosApiStationDto> giosApiStationDtoList = giosClient.getGiosStations();
        for (GiosApiStationDto giosApiStationDto : giosApiStationDtoList) {
            long stationId = 0;
            try {
                stationId = airQualityStationRepository.getByCity(giosApiStationDto.getCity().getName()).getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (airQualityStationRepository.existsById(stationId)) {
                airQualityStationRepository.deleteById(stationId);
            }
            AirQualityStation airQualityStation = giosMapper.mapToStation(giosApiStationDto);
            if (!airQualityStationRepository.existsByCity(giosApiStationDto.getCity().getName())) {
                airQualityStationRepository.save(airQualityStation);
            }
        }
        statisticsService.create(Statistics.builder()
                .status(AIR_QUALITY_STATIONS_UPDATED)
                .remarks("")
                .build());
        return giosApiStationDtoList;
    }

    public List<GiosApiSensorDto> getAndSaveSensorsByStationId(long stationId) {
        List<GiosApiSensorDto> giosApiSensorDtoList = giosClient.getGiosSensors(stationId);
        for (GiosApiSensorDto giosApiSensorDto : giosApiSensorDtoList) {
            AirQualityStation airQualityStation = airQualityStationRepository.getByStationApiId(stationId);
            GiosApiPollutionDto giosApiPollutionDto = giosClient.getGiosPollution(giosApiSensorDto.getId());
            for (GiosApiPollutionValueDto giosApiPollutionValueDto : giosApiPollutionDto.getValues()) {
                if (giosApiPollutionValueDto.getValue() != 0) {
                    AirQualitySensor airQualitySensor = giosMapper.mapToSensor(giosApiSensorDto, giosApiPollutionValueDto);
                    airQualitySensor.setAirQualityStation(airQualityStation);
                    airQualitySensorRepository.save(airQualitySensor);
                    break;
                }
            }
        }
        return giosApiSensorDtoList;
    }

    public GiosApiAirQualityDto getAndSaveAirQualityIndexByStationId(long stationId) {
        GiosApiAirQualityDto giosApiAirQualityDto = giosClient.getGiosAirQuality(stationId);
        AirQualityStation airQualityStation = airQualityStationRepository.getByStationApiId(stationId);
        AirQualityIndex airQualityIndex = giosMapper.mapToAirQuality(giosApiAirQualityDto, stationId);
        airQualityIndex.setAirQualityStation(airQualityStation);
        airQualityIndexRepository.save(airQualityIndex);
        return giosApiAirQualityDto;
    }
}
