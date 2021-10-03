package com.alert.app.backend.api.gios.service;

import com.alert.app.backend.api.gios.client.GiosClient;
import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiosService {

    private final GiosClient giosClient;

    public List<GiosApiStationDto> getAll() {
        List<GiosApiStationDto> giosApiStationDtoList = giosClient.getGiosStations();
//        List<>
//        for ()
        return giosClient.getGiosStations();
    }
}
