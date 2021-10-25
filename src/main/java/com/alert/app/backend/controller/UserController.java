package com.alert.app.backend.controller;

import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.exception.WrongException;
import com.alert.app.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping
    public UserDto getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto create(@RequestBody UserDto userDto) throws WrongException {
        return userService.create(userDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @PutMapping("/logIn")
    public void logIn(@RequestParam String email, @RequestParam String password) {
        userService.logIn(email, password);
    }

    @PutMapping("/logOut")
    public void logOut(@RequestParam String email) {
        userService.logOut(email);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
