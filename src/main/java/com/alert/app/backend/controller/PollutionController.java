package com.alert.app.backend.controller;

import com.alert.app.backend.dto.PollutionDto;
import com.alert.app.backend.service.PollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pollution")
public class PollutionController {

    private final PollutionService service;

    @GetMapping
    public List<PollutionDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public PollutionDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
