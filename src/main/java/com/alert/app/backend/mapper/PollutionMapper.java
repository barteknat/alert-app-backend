package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Pollution;
import com.alert.app.backend.dto.PollutionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollutionMapper {

    public Pollution mapToPollution(PollutionDto pollutionDto) {
        return Pollution.builder()
                .id(pollutionDto.getId())
                .name(pollutionDto.getName())
                .build();
    }

    public PollutionDto mapToPollutionDto(Pollution pollution) {
        return PollutionDto.builder()
                .id(pollution.getId())
                .name(pollution.getName())
                .build();
    }

    public List<PollutionDto> mapToPollutionDtoList(List<Pollution> pollutionList) {
        List<PollutionDto> pollutionDtoList = new ArrayList<>();
        for (Pollution pollution : pollutionList) {
            pollutionDtoList.add(mapToPollutionDto(pollution));
        }
        return pollutionDtoList;
    }
}
