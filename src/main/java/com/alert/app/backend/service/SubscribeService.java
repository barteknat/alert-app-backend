package com.alert.app.backend.service;

import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.domain.*;
import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.mapper.SubscribeMapper;
import com.alert.app.backend.repository.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.alert.app.backend.status.AirQualityStatus.VERY_GOOD;
import static com.alert.app.backend.status.SubscribeStatus.NOT_SUBSCRIBING;
import static com.alert.app.backend.status.SubscribeStatus.SUBSCRIBING;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeMapper subscribeMapper;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;
    private final AirQualityStationRepository airQualityStationRepository;
    private final WeatherStationRepository weatherStationRepository;
    private final AirQualityIndexRepository airQualityIndexRepository;
    private final GiosService giosService;

    public List<SubscribeDto> getAll() {
        return subscribeMapper.mapToSubscribeDtoList(subscribeRepository.findAll());
    }

    public SubscribeDto getById(long id) {
        return subscribeMapper.mapToSubscribeDto(subscribeRepository.getById(id));
    }

    @Transactional
    public SubscribeDto create(long userId, String city) throws NotFoundException {
        Subscribe subscribe = new Subscribe();
        User user = userRepository.getById(userId);
        if (!airQualityStationRepository.existsByCityLike(city) || !weatherStationRepository.existsByCityLike(city))
            throw new NotFoundException("STATION NOT FOUND");
        AirQualityStation airQualityStation = airQualityStationRepository.getByCityLike(city);
        WeatherStation weatherStation = weatherStationRepository.getByCityLike(city);
//        giosService.getSensorsByStationId(airQualityStation.getStationApiId());
//        giosService.getAirQualityIndexByStationId(airQualityStation.getStationApiId());
        subscribe.setUser(user);
        subscribe.setAirQualityStation(airQualityStation);
        subscribe.setWeatherStation(weatherStation);
        user.setSubscribeStatus(SUBSCRIBING);
//        checkSubscribes();
        return subscribeMapper.mapToSubscribeDto(subscribeRepository.save(subscribe));
    }

    public void checkSubscribes() {
        List<Subscribe> subscribes = subscribeRepository.findAll();
        for (Subscribe subscribe : subscribes) {
            long stationId = subscribe.getAirQualityStation().getStationApiId();
            giosService.getAirQualityIndexByStationId(stationId);
            AirQualityIndex airQualityIndex = airQualityIndexRepository.getDistinctFirstByStationApiIdOrderByIdDesc(stationId);
            if (airQualityIndex.getLevelName().equals(VERY_GOOD.getValue())) {
                System.out.println("EMAIL HAS BEEN SENT id: " + airQualityIndex.getId());
            }
        }
//        for (Subscribe subscribe : subscribes) {
//            long stationId = subscribe.getAirQualityStation().getStationApiId();
//            giosService.getAirQualityIndexByStationId(stationId);
//            AirQualityIndex airQualityIndex = airQualityIndexRepository.getByIdAndStationApiIdOrderByIdDesc(2, 2);
//            List<AirQualityIndex> airQualityIndex = airQualityIndexRepository.getAllByStationApiIdOrderByDateDesc(stationId);
//            System.out.println("AIR QUALITY INDEX: " + airQualityIndex);
//            if (airQualityIndex.getLevelName().equals(VERY_GOOD.getValue())) {
//                sendMail();
//            }

//        }
    }

    public void sendMail() {
        System.out.println("EMAIL HAS BEEN SENT");
    }

    @Transactional
    public void delete(long id) {
        User user = subscribeRepository.getById(id).getUser();
        subscribeRepository.deleteById(id);
        user.setSubscribeStatus(NOT_SUBSCRIBING);
    }
}
