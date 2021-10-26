package com.alert.app.backend.service;

import com.alert.app.backend.domain.User;
import com.alert.app.backend.dto.UserDto;
import com.alert.app.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User getUser() {
        return User.builder()
                .username("test")
                .email("test@test.pl")
                .build();
    }

    @Test
    void shouldGetAll() {
        //Given
        long id = userRepository.save(getUser()).getId();

        //When
        List<UserDto> userDtoList = userService.getAll();

        //Then
        assertEquals("test", userDtoList.get(userDtoList.size() - 1).getUsername());
        assertEquals("test@test.pl", userDtoList.get(userDtoList.size() - 1).getEmail());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    void shouldGetByEmail() {
        //Given
        long id = userRepository.save(getUser()).getId();

        //When
        UserDto userDtoList = userService.getByEmail(getUser().getEmail());

        //Then
        assertEquals("test", userDtoList.getUsername());
        assertEquals("test@test.pl", userDtoList.getEmail());

        //CleanUp
        userRepository.deleteById(id);
    }
}
