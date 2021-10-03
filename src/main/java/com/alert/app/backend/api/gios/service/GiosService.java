package com.alert.app.backend.api.gios.service;

import com.alert.app.backend.api.gios.client.GiosClient;
import com.alert.app.backend.api.gios.dto.*;
import com.alert.app.backend.api.gios.mapper.GiosMapper;
import com.alert.app.backend.domain.*;
import com.alert.app.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiosService {

    private final GiosClient giosClient;
    private final GiosMapper giosMapper;
    private final StationRepository stationRepository;
    private final CityRepository cityRepository;
    private final SensorRepository sensorRepository;
    private final PollutionRepository pollutionRepository;
    private final PollutionValueRepository pollutionValueRepository;
    private final AirQualityRepository airQualityRepository;

    public List<GiosApiStationDto> getAllStations() {
        List<GiosApiStationDto> giosApiStationDtoList = giosClient.getGiosStations();
        for (GiosApiStationDto giosApiStationDto : giosApiStationDtoList) {
            City city = giosMapper.mapToCity(giosApiStationDto);
            cityRepository.save(city);
            Station station = giosMapper.mapToStation(giosApiStationDto);
            station.setCity(city);
            stationRepository.save(station);
        }
        return giosClient.getGiosStations();
    }

    public List<GiosApiSensorDto> getSensorsByStationId(long stationId) {
        List<GiosApiSensorDto> giosApiSensorDtoList = giosClient.getGiosSensors(stationId);
        for (GiosApiSensorDto giosApiSensorDto : giosApiSensorDtoList) {
            Station station = stationRepository.getById(stationId);
            Sensor sensor = giosMapper.mapToSensor(giosApiSensorDto);
            sensor.setStation(station);
            sensorRepository.save(sensor);
        }
        return giosApiSensorDtoList;
    }

    public GiosApiPollutionDto getPollutionBySensorId(long sensorId) {
        GiosApiPollutionDto giosApiPollutionDto = giosClient.getGiosPollution(sensorId);
        Sensor sensor = sensorRepository.getById(sensorId);
        Pollution pollution = giosMapper.mapToPollution(giosApiPollutionDto);
        pollution.setSensor(sensor);
        pollutionRepository.save(pollution);
        for (GiosApiPollutionValueDto giosApiPollutionValueDto : giosApiPollutionDto.getValues()) {
            PollutionValue pollutionValue = giosMapper.mapToPollutionValue(giosApiPollutionValueDto);
            pollutionValue.setPollution(pollution);
            pollutionValueRepository.save(pollutionValue);
        }
        return giosApiPollutionDto;
    }

    public GiosApiAirQualityDto getAirQualityByStationId(long stationId) {
        GiosApiAirQualityDto giosApiAirQualityDto = giosClient.getGiosAirQuality(stationId);
        Station station = stationRepository.getById(stationId);
        AirQuality airQuality = giosMapper.mapToAirQuality(giosApiAirQualityDto);
        airQuality.setStation(station);
        airQualityRepository.save(airQuality);
        return giosApiAirQualityDto;
    }
}
