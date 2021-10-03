package com.alert.app.backend.controller;

import com.alert.app.backend.dto.StationDto;
import com.alert.app.backend.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/station")
public class StationController {

    private final StationService service;

    @GetMapping
    public List<StationDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public StationDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
