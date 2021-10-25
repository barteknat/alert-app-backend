package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.alert.app.backend.status.UserStatus.LOGGED_IN;
import static com.alert.app.backend.status.UserStatus.SUBSCRIBING;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUser() {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1)
                .username("test")
                .email("test@test.pl")
                .password("test")
                .build();

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
    }

    @Test
    void mapToUserDto() {
        //Given
        User user = User.builder()
                .id(1)
                .username("test")
                .email("test@test.pl")
                .password("test")
                .logStatus(LOGGED_IN)
                .subStatus(SUBSCRIBING)
                .created(LocalDateTime.now())
                .build();

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getLogStatus(), userDto.getLogStatus());
        assertEquals(user.getSubStatus(), userDto.getSubStatus());
        assertEquals(user.getCreated(), userDto.getCreated());
    }

    @Test
    void mapToUserDtoList() {
        //Given
        List<User> userList = Arrays.asList(new User());

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals(1, userDtoList.size());
    }
}