package com.alert.app.backend.service;

import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.mapper.UserMapper;
import com.alert.app.backend.repository.UserRepository;
import com.alert.app.backend.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.alert.app.backend.status.Status.NOT_SUBSCRIBING;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDto> getAll() {
        return userMapper.mapToUserList(userRepository.findAll());
    }

    public UserDto getById(long id) {
        return userMapper.mapToUserDto(userRepository.getById(id));
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userRepository.save(userMapper.mapToUser(userDto));
        user.setStatus(NOT_SUBSCRIBING);
        user.setCreated(LocalDateTime.now());
        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User userBeforeUpdate = userRepository.getById(userDto.getId());
        Status status = userBeforeUpdate.getStatus();
        LocalDateTime created = userBeforeUpdate.getCreated();
        User userUpdated = userRepository.save(userMapper.mapToUser(userDto));
        userUpdated.setStatus(status);
        userUpdated.setCreated(created);
        return userMapper.mapToUserDto(userUpdated);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
