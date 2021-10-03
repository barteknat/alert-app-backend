package com.alert.app.backend.controller;

import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto create(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody UserDto userDto) {
        return service.update(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }


}
