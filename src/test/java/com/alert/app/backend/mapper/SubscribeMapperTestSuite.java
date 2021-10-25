package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.dto.SubscribeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscribeMapperTestSuite {

    @Autowired
    private SubscribeMapper subscribeMapper;

    @Test
    void shouldMapToSubscribeDto() {
        //Given
        Subscribe subscribe = Subscribe.builder()
                .id(1)
                .city("test")
                .build();

        //When
        SubscribeDto subscribeDto = subscribeMapper.mapToSubscribeDto(subscribe);

        //Then
        assertEquals(subscribe.getId(), subscribeDto.getId());
        assertEquals(subscribe.getCity(), subscribeDto.getCity());
    }

    @Test
    void shouldMapToSubscribeDtoList() {
        //Given
        List<Subscribe> subscribeList = Arrays.asList(new Subscribe());

        //When
        List<SubscribeDto> subscribeDtoList = subscribeMapper.mapToSubscribeDtoList(subscribeList);

        //Then
        assertEquals(1, subscribeDtoList.size());
    }
}