package com.alert.app.backend.service;

import com.alert.app.backend.domain.*;
import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.exception.DuplicateException;
import com.alert.app.backend.exception.WrongException;
import com.alert.app.backend.mapper.SubscribeMapper;
import com.alert.app.backend.repository.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.SUBSCRIBE_CREATED;
import static com.alert.app.backend.status.UserStatus.*;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeMapper subscribeMapper;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;
    private final AirQualityStationRepository airQualityStationRepository;
    private final WeatherStationRepository weatherStationRepository;
    private final StatisticsService statisticsService;

    public List<SubscribeDto> getAll() {
        return subscribeMapper.mapToSubscribeDtoList(subscribeRepository.findAll());
    }

    public List<SubscribeDto> getAllByUserId(long userId) {
        return subscribeMapper.mapToSubscribeDtoList(subscribeRepository.findAllByUserId(userId));
    }

    public SubscribeDto getByUserIdAndCity(long userId, String city) {
        try {
            return subscribeMapper.mapToSubscribeDto(subscribeRepository.getByUserIdAndAirQualityStation_City(userId, city));
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public SubscribeDto create(long userId, String city) throws NotFoundException, DuplicateException, WrongException {
        if (!userRepository.existsById(userId)) throw new NotFoundException("USER NOT FOUND");
        if (!airQualityStationRepository.existsByCityLike(city)) throw new NotFoundException("CITY NOT FOUND");
        if (!userRepository.getById(userId).getLogStatus().equals(LOGGED_IN)) throw new WrongException("YOU HAVE TO BE LOG_IN FIRST");
        if (subscribeRepository.existsByUserIdAndAirQualityStation_City(userId, city)) throw new DuplicateException("YOU ARE ALREADY SUBSCRIBING THIS CITY");
        Subscribe subscribe = new Subscribe();
        User user = userRepository.getById(userId);
        AirQualityStation airQualityStation = airQualityStationRepository.getByCityLike(city);
        WeatherStation weatherStation = weatherStationRepository.getDistinctFirstByCityOrderByIdDesc(city);
        subscribe.setCity(airQualityStation.getCity());
        subscribe.setUser(user);
        subscribe.setAirQualityStation(airQualityStation);
        subscribe.setWeatherStation(weatherStation);
        user.setSubStatus(SUBSCRIBING);
        statisticsService.create(SUBSCRIBE_CREATED, "");
        return subscribeMapper.mapToSubscribeDto(subscribeRepository.save(subscribe));
    }

    @Transactional
    public void delete(long id) {
        User user = subscribeRepository.getById(id).getUser();
        subscribeRepository.deleteById(id);
        if (!subscribeRepository.existsByUserId(user.getId())) {
            user.setSubStatus(NOT_SUBSCRIBING);
        }
    }
}
