package com.alert.app.backend.api.gios.client;

import com.alert.app.backend.api.gios.config.GiosConfig;
import com.alert.app.backend.api.gios.dto.GiosApiAirQualityDto;
import com.alert.app.backend.api.gios.dto.GiosApiPollutionDto;
import com.alert.app.backend.api.gios.dto.GiosApiSensorDto;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiosClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final GiosConfig giosConfig;

    public List<GiosApiStationDto> getGiosStations() {
        try {
            List<GiosApiStationDto> giosApiStationDtoList = Optional.ofNullable(restTemplate.getForObject(getGetStationsUri(), GiosApiStationDto[].class))
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(giosApiStationDto -> Objects.nonNull(giosApiStationDto.getId()) && Objects.nonNull(giosApiStationDto.getStationName()))
                    .collect(Collectors.toList());
            System.out.println(giosApiStationDtoList);
            return giosApiStationDtoList;
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI getGetStationsUri() {
        return UriComponentsBuilder.fromHttpUrl(giosConfig.getGiosApiEndpoint() + "/station/findAll")
                .build().encode().toUri();
    }

    public List<GiosApiSensorDto> getGiosSensors(long stationId) {
        try {
            List<GiosApiSensorDto> giosApiSensorDtoList = Optional.ofNullable(restTemplate.getForObject(getGetSensorsUri(stationId), GiosApiSensorDto[].class))
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(giosApiSensorDto -> Objects.nonNull(giosApiSensorDto.getId()) && Objects.nonNull(giosApiSensorDto.getParam()))
                    .collect(Collectors.toList());
            System.out.println(giosApiSensorDtoList);
            return giosApiSensorDtoList;
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI getGetSensorsUri(long stationId) {
        return UriComponentsBuilder.fromHttpUrl(giosConfig.getGiosApiEndpoint() + "/station/sensors/" + stationId)
                .build().encode().toUri();
    }

    public GiosApiPollutionDto getGiosPollution(long sensorId) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(getGetPollutionUri(sensorId), GiosApiPollutionDto.class))
                    .orElse(new GiosApiPollutionDto());
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new GiosApiPollutionDto();
        }
    }

    private URI getGetPollutionUri(long sensorId) {
        return UriComponentsBuilder.fromHttpUrl(giosConfig.getGiosApiEndpoint() + "/data/getData/" + sensorId)
                .build().encode().toUri();
    }

    public GiosApiAirQualityDto getGiosAirQuality(long stationId) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(getGetAirQualityUri(stationId), GiosApiAirQualityDto.class))
                    .orElse(new GiosApiAirQualityDto());
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new GiosApiAirQualityDto();
        }
    }

    private URI getGetAirQualityUri(long stationId) {
        return UriComponentsBuilder.fromHttpUrl(giosConfig.getGiosApiEndpoint() + "/aqindex/getIndex/" + stationId)
                .build().encode().toUri();
    }
}
