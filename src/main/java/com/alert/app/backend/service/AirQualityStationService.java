package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.dto.AirQualityStationDto;
import com.alert.app.backend.exception.UpdateException;
import com.alert.app.backend.mapper.AirQualityStationMapper;
import com.alert.app.backend.repository.AirQualityStationRepository;
import com.alert.app.backend.repository.WeatherStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirQualityStationService {

    private final AirQualityStationMapper airQualityStationMapper;
    private final AirQualityStationRepository airQualityStationRepository;
    private final GiosService giosService;
    @Autowired
    private final WeatherStationRepository weatherStationRepository;

    public List<AirQualityStationDto> getAll() throws UpdateException {
        giosService.getAndSaveAllStations();
        return airQualityStationMapper.mapToStationDtoList(airQualityStationRepository.findAll());
    }

//    public List<AirQualityStationDto> getAllByCity() {
//        List<AirQualityStationDto> airQualityStationDtos = new ArrayList<>();
//        for (AirQualityStationDto airQualityStationDto : airQualityStationMapper.mapToStationDtoList(airQualityStationRepository.findAll())) {
//            try {
//                if (airQualityStationDto.getCity().equals(weatherStationRepository.getByCity(airQualityStationDto.getCity()).getCity()))
//                    airQualityStationDtos.add(airQualityStationDto);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        for (AirQualityStationDto cities : airQualityStationDtos) {
//            System.out.println("THE CITIES " + cities.getCity());
//        }
//        return airQualityStationDtos;
//    }

//    public AirQualityStationDto getById(long id) {
//        return airQualityStationMapper.mapToStationDto(airQualityStationRepository.getById(id));
//    }
//
//    public AirQualityStationDto getByCity(String city) {
//        return airQualityStationMapper.mapToStationDto(airQualityStationRepository.getByCity(city));
//    }
//
//    @Transactional
//    public void delete(long id) {
//        airQualityStationRepository.deleteById(id);
//    }
}
