package com.alert.app.backend.mail.service;

import com.alert.app.backend.domain.AirQualityIndex;
import com.alert.app.backend.domain.Subscribe;
import com.alert.app.backend.mail.domain.Mail;
import org.springframework.stereotype.Service;

import static com.alert.app.backend.mail.domain.AlertMessage.ALERT_MESSAGE;

@Service
public class MailCreatorService {

    public Mail buildSubscribeAlertMail(Subscribe subscribe, AirQualityIndex airQualityIndex) {
        return Mail.builder()
                .mailTo(subscribe.getUser().getEmail())
                .subject(ALERT_MESSAGE.getSubscribeAlertSubject())
                .message(ALERT_MESSAGE.getSubscribeAlertMessage(subscribe, airQualityIndex))
                .build();
    }
}
