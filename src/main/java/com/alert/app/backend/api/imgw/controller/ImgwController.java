package com.alert.app.backend.api.imgw.controller;

import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.service.ImgwService;
import com.alert.app.backend.exception.UpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/imgw")
public class ImgwController {

    private final ImgwService imgwService;

    @GetMapping("/stations")
    public List<ImgwApiStationDto> getAndSaveAllStations() throws UpdateException {
        return imgwService.getAndSaveAllStations();
    }
}
