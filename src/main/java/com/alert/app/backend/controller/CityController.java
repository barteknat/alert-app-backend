package com.alert.app.backend.controller;

import com.alert.app.backend.dto.CityDto;
import com.alert.app.backend.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/city")
public class CityController {

    private final CityService service;

    @GetMapping
    public List<CityDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public CityDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
