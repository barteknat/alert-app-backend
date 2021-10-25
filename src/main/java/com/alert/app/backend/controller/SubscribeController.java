package com.alert.app.backend.controller;

import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.exception.DuplicateException;
import com.alert.app.backend.exception.WrongException;
import com.alert.app.backend.service.SubscribeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @GetMapping("/all")
    public List<SubscribeDto> getAll() {
        return subscribeService.getAll();
    }

    @GetMapping("/allById")
    public List<SubscribeDto> getAllByUserId(@RequestParam long userId) {
        return subscribeService.getAllByUserId(userId);
    }

    @GetMapping
    public SubscribeDto getByUserIdAndCity(@RequestParam long userId, @RequestParam String city) {
        return subscribeService.getByUserIdAndCity(userId, city);
    }

    @PostMapping
    public SubscribeDto create(@RequestParam long userId, @RequestParam String city) throws NotFoundException, DuplicateException, WrongException {
        return subscribeService.create(userId, city);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        subscribeService.delete(id);
    }
}
