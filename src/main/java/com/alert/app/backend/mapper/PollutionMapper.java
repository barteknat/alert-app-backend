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
                .sulphurDioxide(pollutionDto.getSulphurDioxide())
                .nitrogenDioxide(pollutionDto.getNitrogenDioxide())
                .pM10(pollutionDto.getPM10())
                .pM2_5(pollutionDto.getPM2_5())
                .carbonMonoxide(pollutionDto.getCarbonMonoxide())
                .benzene(pollutionDto.getBenzene())
                .ozone(pollutionDto.getOzone())
                .build();
    }

    public PollutionDto mapToPollutionDto(Pollution pollution) {
        return PollutionDto.builder()
                .id(pollution.getId())
                .name(pollution.getName())
                .sulphurDioxide(pollution.getSulphurDioxide())
                .nitrogenDioxide(pollution.getNitrogenDioxide())
                .pM10(pollution.getPM10())
                .pM2_5(pollution.getPM2_5())
                .carbonMonoxide(pollution.getCarbonMonoxide())
                .benzene(pollution.getBenzene())
                .ozone(pollution.getOzone())
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
