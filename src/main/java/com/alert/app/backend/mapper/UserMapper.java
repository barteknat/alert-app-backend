package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .logStatus(user.getLogStatus())
                .subStatus(user.getSubStatus())
                .created(user.getCreated())
                .build();
    }

    public List<UserDto> mapToUserList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(mapToUserDto(user));
        }
        return userDtoList;
    }
}
