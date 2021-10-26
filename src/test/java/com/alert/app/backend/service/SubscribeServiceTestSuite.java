package com.alert.app.backend.service;

import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.dto.SubscribeDto;
import com.alert.app.backend.repository.SubscribeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscribeServiceTestSuite {

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Test
    void shouldGetAll() {
        //Given
        Subscribe subscribe = Subscribe.builder()
                .userEmail("test@test.pl")
                .city("test")
                .build();
        long id = subscribeRepository.save(subscribe).getId();

        //When
        List<SubscribeDto> subscribeList = subscribeService.getAll();

        //Then
        assertEquals("test@test.pl", subscribeList.get(subscribeList.size()-1).getUserEmail());
        assertEquals("test", subscribeList.get(subscribeList.size()-1).getCity());

        //CleanUp
        subscribeRepository.deleteById(id);
    }
}
