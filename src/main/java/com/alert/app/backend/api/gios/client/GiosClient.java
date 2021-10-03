package com.alert.app.backend.api.gios.client;

import com.alert.app.backend.api.gios.config.GiosConfig;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.mapper.GiosMapper;
import com.alert.app.backend.repository.CityRepository;
import com.alert.app.backend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GiosClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiosClient.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final GiosConfig giosConfig;
    private final StationRepository stationRepository;
    private final CityRepository cityRepository;
    private final GiosMapper giosMapper;

    public List<GiosApiStationDto> getGiosStations() {
        try {
            List<GiosApiStationDto> giosApiStationDtoList = Optional.ofNullable(restTemplate.getForObject(getUri(), GiosApiStationDto[].class))
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(giosApiStationDto -> Objects.nonNull(giosApiStationDto.getId()) && Objects.nonNull(giosApiStationDto.getStationName()))
                    .collect(Collectors.toList());
            System.out.println(giosApiStationDtoList);
            for (GiosApiStationDto giosApiStationDto : giosApiStationDtoList) {
                stationRepository.save(giosMapper.mapToStation(giosApiStationDto));
                cityRepository.save(giosMapper.mapToCity(giosApiStationDto));
            }
            return giosApiStationDtoList;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI getUri() {
        return UriComponentsBuilder.fromHttpUrl(giosConfig.getGiosApiEndpoint() + "/station/findAll")
                .build().encode().toUri();
    }
}
