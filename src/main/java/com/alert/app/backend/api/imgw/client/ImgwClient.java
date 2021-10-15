package com.alert.app.backend.api.imgw.client;

import com.alert.app.backend.api.imgw.config.ImgwConfig;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
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
public class ImgwClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ImgwConfig imgwConfig;

    public List<ImgwApiStationDto> getImgwStations() {
        try {
            List<ImgwApiStationDto> imgwApiStationDtoList = Optional.ofNullable(restTemplate.getForObject(getGetStationsUri(), ImgwApiStationDto[].class))
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(imgwApiStationDto -> Objects.nonNull(imgwApiStationDto.getId_stacji()) && Objects.nonNull(imgwApiStationDto.getStacja()))
                    .collect(Collectors.toList());
            return imgwApiStationDtoList;
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI getGetStationsUri() {
        return UriComponentsBuilder.fromHttpUrl(imgwConfig.getImgwApiEndpoint())
                .build().encode().toUri();
    }
}
