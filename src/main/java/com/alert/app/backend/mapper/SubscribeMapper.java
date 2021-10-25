package com.alert.app.backend.mapper;

import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.dto.SubscribeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeMapper {

    public SubscribeDto mapToSubscribeDto(Subscribe subscribe) {
        return SubscribeDto.builder()
                .id(subscribe.getId())
                .city(subscribe.getCity())
                .build();
    }

    public List<SubscribeDto> mapToSubscribeDtoList(List<Subscribe> subscribeList) {
        List<SubscribeDto> subscribeDtoList = new ArrayList<>();
        for (Subscribe subscribe : subscribeList) {
            subscribeDtoList.add(mapToSubscribeDto(subscribe));
        }
        return subscribeDtoList;
    }
}
