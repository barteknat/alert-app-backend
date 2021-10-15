package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.WeatherStation;
import com.alert.app.backend.dto.WeatherStationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherStationMapper {

    public WeatherStationDto mapToWeatherStationDto(WeatherStation weatherStation) {
        return WeatherStationDto.builder()
                .id(weatherStation.getId())
                .stationId(weatherStation.getStationId())
                .city(weatherStation.getCity())
                .date(weatherStation.getDate())
                .temperature(weatherStation.getTemperature())
                .windSpeed(weatherStation.getWindSpeed())
                .windDirection(weatherStation.getWindDirection())
                .humidity(weatherStation.getHumidity())
                .rainAmount(weatherStation.getRainAmount())
                .pressure(weatherStation.getPressure())
                .build();
    }

    public List<WeatherStationDto> mapToWeatherStationDtoList(List<WeatherStation> weatherStationList) {
        List<WeatherStationDto> weatherStationDtoList = new ArrayList<>();
        for (WeatherStation weatherStation : weatherStationList) {
            weatherStationDtoList.add(mapToWeatherStationDto(weatherStation));
        }
        return weatherStationDtoList;
    }
}
