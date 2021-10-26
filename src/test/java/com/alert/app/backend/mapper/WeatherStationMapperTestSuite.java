package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.dto.WeatherStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherStationMapperTestSuite {

    @Autowired
    private WeatherStationMapper weatherStationMapper;

    @Test
    void shouldMapToWeatherStationDto() {
        //Given
        WeatherStation weatherStation = WeatherStation.builder()
                .id(1)
                .stationId(1)
                .city("test")
                .date(LocalDate.now())
                .time(1)
                .temperature(1)
                .windSpeed(1)
                .windDirection(1)
                .humidity(1)
                .rainAmount(1)
                .pressure(1)
                .build();
        //When
        WeatherStationDto weatherStationDto = weatherStationMapper.mapToWeatherStationDto(weatherStation);

        //Then
        assertEquals(weatherStation.getId(), weatherStationDto.getId());
        assertEquals(weatherStation.getStationId(), weatherStationDto.getStationId());
        assertEquals(weatherStation.getCity(), weatherStationDto.getCity());
        assertEquals(weatherStation.getDate(), weatherStationDto.getDate());
        assertEquals(weatherStation.getTime(), weatherStationDto.getTime());
        assertEquals(weatherStation.getTemperature(), weatherStationDto.getTemperature());
        assertEquals(weatherStation.getWindSpeed(), weatherStationDto.getWindSpeed());
        assertEquals(weatherStation.getWindDirection(), weatherStationDto.getWindDirection());
        assertEquals(weatherStation.getHumidity(), weatherStationDto.getHumidity());
        assertEquals(weatherStation.getRainAmount(), weatherStationDto.getRainAmount());
        assertEquals(weatherStation.getPressure(), weatherStationDto.getPressure());
    }

    @Test
    void shouldMapToWeatherStationDtoList() {
        //Given
        List<WeatherStation> weatherStationList = Arrays.asList(new WeatherStation());

        //When
        List<WeatherStationDto> weatherStationDtoList = weatherStationMapper.mapToWeatherStationDtoList(weatherStationList);

        //Then
        assertEquals(1, weatherStationDtoList.size());
    }
}
