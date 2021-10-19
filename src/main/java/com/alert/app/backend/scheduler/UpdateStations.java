package com.alert.app.backend.scheduler;

import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.api.imgw.service.ImgwService;
import com.alert.app.backend.exception.UpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStations {

    private final GiosService giosService;
    private final ImgwService imgwService;

//    @Scheduled(fixedDelay = 10000)
    public void updateStations() throws UpdateException {
        imgwService.getAndSaveAllStations();
        giosService.getAndSaveAllStations();
    }
}
