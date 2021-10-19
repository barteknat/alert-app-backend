package com.alert.app.backend.api.gios.service;

import com.alert.app.backend.AlertApplication;
import com.alert.app.backend.api.gios.client.GiosClient;
import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.api.gios.mapper.GiosMapper;
import com.alert.app.backend.domain.*;
import com.alert.app.backend.exception.UpdateException;
import com.alert.app.backend.repository.*;
import com.alert.app.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.*;

@Service
@RequiredArgsConstructor
public class GiosService {

    private final GiosClient giosClient;
    private final GiosMapper giosMapper;
    private final AirQualityStationRepository airQualityStationRepository;
    private final AirQualitySensorRepository airQualitySensorRepository;
    private final AirQualityIndexRepository airQualityIndexRepository;
    private final WeatherStationRepository weatherStationRepository;
    private final SubscribeRepository subscribeRepository;
    private final StatisticsService statisticsService;

    @Transactional
    public List<GiosApiStationDto> getAndSaveAllStations() throws UpdateException {
        if (!subscribeRepository.findAll().isEmpty() || !airQualitySensorRepository.findAll().isEmpty() || !airQualityIndexRepository.findAll().isEmpty()) throw new UpdateException("STATIONS CANNOT BE UPDATED");
        List<GiosApiStationDto> giosApiStationDtoList = giosClient.getGiosStations();
        List<WeatherStation> weatherStationList = weatherStationRepository.findAll();
        for (GiosApiStationDto giosApiStationDto : giosApiStationDtoList) {
            AirQualityStation airQualityStation = giosMapper.mapToStation(giosApiStationDto);
            try {
                long stationId = airQualityStationRepository.getByCity(giosApiStationDto.getCity().getName()).getId();
                if (airQualityStationRepository.existsById(stationId)) {
                    airQualityStationRepository.deleteById(stationId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (weatherStationList.contains(weatherStationRepository.getByCity(giosApiStationDto.getCity().getName()))) {
                airQualityStationRepository.save(airQualityStation);
            }
        }
        statisticsService.create(AIR_QUALITY_STATIONS_UPDATED, "");
        return giosApiStationDtoList;
    }

    public List<GiosApiSensorDto> getAndSaveSensorsByStationId(long stationId) {
        try {
            List<AirQualitySensor> airQualitySensorList = airQualitySensorRepository.getByStationApiId(stationId);
            for (AirQualitySensor airQualitySensor : airQualitySensorList) {
                airQualitySensorRepository.deleteById(airQualitySensor.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        statisticsService.create(AIR_QUALITY_SENSORS_UPDATED, "");
        return giosApiSensorDtoList;
    }

    public GiosApiAirQualityDto getAndSaveAirQualityIndexByStationId(long stationId) {
        try {
            long id = airQualityIndexRepository.getByStationApiId(stationId).getId();
            if (airQualityIndexRepository.existsById(id)) {
                airQualityIndexRepository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GiosApiAirQualityDto giosApiAirQualityDto = giosClient.getGiosAirQuality(stationId);
        AirQualityStation airQualityStation = airQualityStationRepository.getByStationApiId(stationId);
        AirQualityIndex airQualityIndex = giosMapper.mapToAirQuality(giosApiAirQualityDto, stationId);
        airQualityIndex.setAirQualityStation(airQualityStation);
        airQualityIndexRepository.save(airQualityIndex);
        statisticsService.create(AIR_QUALITY_INDEXES_UPDATED, "");
        return giosApiAirQualityDto;
    }
}
