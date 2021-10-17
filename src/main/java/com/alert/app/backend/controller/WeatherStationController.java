//package com.alert.app.backend.controller;
//
//import com.alert.app.backend.dto.WeatherStationDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/v1/weatherStation")
//public class WeatherStationController {
//
//    private final WeatherStationService weatherStationService;
//
//    @GetMapping(value = "/city/{city}")
//    public WeatherStationDto getByCity(@PathVariable String city) {
//        return imgwService.getByCity(city);
//    }
//}
