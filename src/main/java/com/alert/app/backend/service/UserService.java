package com.alert.app.backend.service;

import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.exception.WrongException;
import com.alert.app.backend.mapper.UserMapper;
import com.alert.app.backend.repository.UserRepository;
import com.alert.app.backend.status.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.alert.app.backend.status.StatisticsStatus.*;
import static com.alert.app.backend.status.UserStatus.*;

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

    public UserDto getByUsername(String username) {
        try {
            return userMapper.mapToUserDto(userRepository.getByUsername(username));
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto getByEmail(String email) {
        try {
            return userMapper.mapToUserDto(userRepository.getByEmail(email));
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public UserDto create(UserDto userDto) throws WrongException {
        if(userRepository.existsByEmail(userDto.getEmail())) throw new WrongException("USER ALREADY EXISTS");
        User user = userRepository.save(userMapper.mapToUser(userDto));
        user.setSubStatus(NOT_SUBSCRIBING);
        user.setLogStatus(LOGGED_OUT);
        user.setCreated(LocalDateTime.now());
        statisticsService.create(USER_CREATED, "");
        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        if (!userRepository.existsById((userDto.getId()))) return new UserDto();
        User userBeforeUpdate = userRepository.getById(userDto.getId());
        UserStatus log = userBeforeUpdate.getLogStatus();
        UserStatus sub = userBeforeUpdate.getSubStatus();
        LocalDateTime created = userBeforeUpdate.getCreated();
        User userUpdated = userRepository.save(userMapper.mapToUser(userDto));
        userUpdated.setLogStatus(log);
        userUpdated.setSubStatus(sub);
        userUpdated.setCreated(created);
        statisticsService.create(USER_UPDATED, "");
        return userMapper.mapToUserDto(userUpdated);
    }

    @Transactional
    public void logIn(String email, String password) throws WrongException {
        if (!userRepository.existsByEmail(email)) return;
        User user = userRepository.getByEmail(email);
        if (!user.getPassword().equals(password)) {
            statisticsService.create(LOGIN_FAILURE, user.getEmail());
            return;
        }
        user.setLogStatus(LOGGED_IN);
        statisticsService.create(LOGIN_SUCCESS, user.getEmail());
    }

    @Transactional
    public void logOut(String email) throws WrongException {
        if (!userRepository.existsByEmail(email)) return;
        User user = userRepository.getByEmail(email);
        user.setLogStatus(LOGGED_OUT);
        statisticsService.create(LOGOUT_SUCCESS, user.getEmail());
    }

//    @Transactional
//    public

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
