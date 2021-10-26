package com.alert.app.backend.service;

import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.dto.WeatherStationDto;
import com.alert.app.backend.repository.WeatherStationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherStationServiceTestSuite {

    @Autowired
    private WeatherStationService weatherStationService;

    @Autowired
    private WeatherStationRepository weatherStationRepository;

    private WeatherStation getWeatherStation() {
        return WeatherStation.builder()
                .city("test")
                .temperature(1)
                .build();
    }

    @Test
    void shouldGetAll() {
        //Given
        long id = weatherStationRepository.save(getWeatherStation()).getId();

        //When
        List<WeatherStationDto> weatherStationDtoList = weatherStationService.getAll();

        //Then
        assertEquals("test", weatherStationDtoList.get(weatherStationDtoList.size() - 1).getCity());
        assertEquals(1, weatherStationDtoList.get(weatherStationDtoList.size() - 1).getTemperature());

        //CleanUp
        weatherStationRepository.deleteById(id);
    }

    @Test
    void shouldGetByCity() {
        //Given
        long id = weatherStationRepository.save(getWeatherStation()).getId();

        //When
        WeatherStationDto weatherStationDto = weatherStationService.getByCity("test");

        //Then
        assertNotNull(weatherStationDto);

        //CleanUp
        weatherStationRepository.deleteById(id);
    }
}
