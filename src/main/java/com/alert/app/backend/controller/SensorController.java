package com.alert.app.backend.controller;

import com.alert.app.backend.dto.SensorDto;
import com.alert.app.backend.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sensor")
public class SensorController {

    private final SensorService service;

    @GetMapping
    public List<SensorDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public SensorDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
