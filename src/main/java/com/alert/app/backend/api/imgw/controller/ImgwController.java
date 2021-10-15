package com.alert.app.backend.api.imgw.controller;

import com.alert.app.backend.api.imgw.dto.ImgwApiStationDto;
import com.alert.app.backend.api.imgw.service.ImgwService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/imgw")
public class ImgwController {

    private final ImgwService imgwService;

    @GetMapping("/stations")
    public List<ImgwApiStationDto> getAllStations() {
        return imgwService.getAllStations();
    }
}
