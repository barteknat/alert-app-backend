package com.alert.app.backend.api.imgw.controller;

import com.alert.app.backend.api.facade.ApiFacade;
import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/imgw")
public class ImgwController {

    private final ApiFacade apiFacade;

    @GetMapping("/stations")
    public List<ImgwApiStationDto> setAllStations() {
        return apiFacade.getAllWeatherStations();
    }
}
