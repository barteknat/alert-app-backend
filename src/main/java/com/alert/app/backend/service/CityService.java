package com.alert.app.backend.service;

import com.alert.app.backend.domain.City;
import com.alert.app.backend.dto.CityDto;
import com.alert.app.backend.mapper.CityMapper;
import com.alert.app.backend.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityMapper cityMapper;

    private final CityRepository cityRepository;

    public List<CityDto> getAll() {
        return cityMapper.mapToCityDtoList(cityRepository.findAll());
    }

    public CityDto getById(long id) {
        return cityMapper.mapToCityDto(cityRepository.getById(id));
    }

    @Transactional
    public CityDto create(String name) {
        City city = new City();
        city.setName(name);
        return cityMapper.mapToCityDto(cityRepository.save(city));
    }

    @Transactional
    public void delete(long id) {
        cityRepository.deleteById(id);
    }
}
