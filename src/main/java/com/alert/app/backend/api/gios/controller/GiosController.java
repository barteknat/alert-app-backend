package com.alert.app.backend.api.gios.controller;

import com.alert.app.backend.api.gios.dto.GiosApiStationDto;
import com.alert.app.backend.api.gios.service.GiosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gios")
public class GiosController {

    private final GiosService giosService;

    @GetMapping
    public List<GiosApiStationDto> getAll() {
        return giosService.getAll();
    }
}
