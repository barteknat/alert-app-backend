package com.alert.app.backend.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.mapper.UserMapper;
import com.alert.app.backend.repository.UserRepository;
import com.alert.app.backend.status.SubscribeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.*;
import static com.alert.app.backend.status.SubscribeStatus.NOT_SUBSCRIBING;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final StatisticsService statisticsService;

    public List<UserDto> getAll() {
        return userMapper.mapToUserList(userRepository.findAll());
    }

    public UserDto getById(long id) {
        return userMapper.mapToUserDto(userRepository.getById(id));
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userRepository.save(userMapper.mapToUser(userDto));
        user.setSubscribeStatus(NOT_SUBSCRIBING);
        user.setCreated(LocalDateTime.now());
        statisticsService.create(USER_CREATED, "");
        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User userBeforeUpdate = userRepository.getById(userDto.getId());
        SubscribeStatus subscribeStatus = userBeforeUpdate.getSubscribeStatus();
        LocalDateTime created = userBeforeUpdate.getCreated();
        User userUpdated = userRepository.save(userMapper.mapToUser(userDto));
        userUpdated.setSubscribeStatus(subscribeStatus);
        userUpdated.setCreated(created);
        statisticsService.create(USER_UPDATED, "");
        return userMapper.mapToUserDto(userUpdated);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
