package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.City;
import com.alert.app.backend.dto.CityDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityMapper {

    public City mapToCity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .build();
    }

    public CityDto mapToCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    public List<CityDto> mapToCityDtoList(List<City> cityList) {
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city : cityList) {
            cityDtoList.add(mapToCityDto(city));
        }
        return cityDtoList;
    }
}
