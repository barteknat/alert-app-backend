package com.alert.app.backend.service;

import com.alert.app.backend.domain.City;
import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.mapper.SubscribeMapper;
import com.alert.app.backend.repository.CityRepository;
import com.alert.app.backend.repository.SubscribeRepository;
import com.alert.app.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.alert.app.backend.status.Status.NOT_SUBSCRIBING;
import static com.alert.app.backend.status.Status.SUBSCRIBING;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeMapper subscribeMapper;

    private final SubscribeRepository subscribeRepository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    public List<SubscribeDto> getAll() {
        return subscribeMapper.mapToSubscribeDtoList(subscribeRepository.findAll());
    }

    public SubscribeDto getById(long id) {
        return subscribeMapper.mapToSubscribeDto(subscribeRepository.getById(id));
    }

    @Transactional
    public SubscribeDto create(long userId, long cityId) {
        if (subscribeRepository.existsByUserId(userId)) return new SubscribeDto();
        User user = userRepository.getById(userId);
        City city = cityRepository.getById(cityId);
        Subscribe subscribe = new Subscribe();
        subscribe.setUser(user);
        subscribe.setCity(city);
        user.setStatus(SUBSCRIBING);
        return subscribeMapper.mapToSubscribeDto(subscribeRepository.save(subscribe));
    }

    @Transactional
    public void delete(long id) {
        User user = subscribeRepository.getById(id).getUser();
        subscribeRepository.deleteById(id);
        user.setStatus(NOT_SUBSCRIBING);
    }
}
