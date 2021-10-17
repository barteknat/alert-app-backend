package com.alert.app.backend.controller;

import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.service.SubscribeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscribe")
public class SubscribeController {

    private final SubscribeService service;

    @GetMapping
    public List<SubscribeDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public SubscribeDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public SubscribeDto create(@RequestParam long userId, @RequestParam String city) throws NotFoundException {
        return service.create(userId, city);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
