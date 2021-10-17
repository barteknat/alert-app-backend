package com.alert.app.backend.scheduler;

import com.alert.app.backend.api.gios.service.GiosService;
import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.mail.domain.Mail;
import com.alert.app.backend.mail.service.MailCreatorService;
import com.alert.app.backend.mail.service.MailSendingService;
import com.alert.app.backend.repository.AirQualityIndexRepository;
import com.alert.app.backend.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.alert.app.backend.status.AirQualityStatus.*;

@Component
@RequiredArgsConstructor
public class SubscribeScheduler {

    private final SubscribeRepository subscribeRepository;
    private final GiosService giosService;
    private final AirQualityIndexRepository airQualityIndexRepository;
    private final MailSendingService mailSendingService;
    private final MailCreatorService mailCreatorService;

    //    @Scheduled(cron = "0 0 8 * * *")
//    @Scheduled(fixedDelay = 10000)
    public void checkSubscribes() {
        List<Subscribe> subscribes = subscribeRepository.findAll();
        for (Subscribe subscribe : subscribes) {
            long stationId = subscribe.getAirQualityStation().getStationApiId();
            giosService.getAndSaveAirQualityIndexByStationId(stationId);
            AirQualityIndex airQualityIndex = airQualityIndexRepository.getDistinctFirstByStationApiIdOrderByIdDesc(stationId);
            if (airQualityIndex.getLevelName().equals(VERY_BAD.getValue()) || airQualityIndex.getLevelName().equals(BAD.getValue())) {
                mailSendingService.sendMailMessage(mailCreatorService.buildSubscribeAlertMail(subscribe, airQualityIndex));
            }
        }
    }
}


