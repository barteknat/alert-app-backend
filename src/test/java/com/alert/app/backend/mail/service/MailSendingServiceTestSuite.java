package com.alert.app.backend.mail.service;

import com.alert.app.backend.domain.*;
import com.alert.app.backend.mail.domain.Mail;
import com.alert.app.backend.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailSendingServiceTestSuite {

    @InjectMocks
    private MailSendingService mailSendingService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private StatisticsService statisticsService;

    @Test
    void shouldSendMailMessage() {
        //Given
        User user = User.builder()
                .email("test@test.com")
                .build();
        AirQualityStation airQualityStation = AirQualityStation.builder()
                .city("test")
                .build();
        WeatherStation weatherStation = WeatherStation.builder()
                .temperature(1)
                .windSpeed(1)
                .humidity(1)
                .pressure(1)
                .build();
        AirQualityIndex airQualityIndex = AirQualityIndex.builder()
                .levelName("Zły")
                .build();
        Subscribe subscribe = Subscribe.builder()
                .user(user)
                .airQualityStation(airQualityStation)
                .weatherStation(weatherStation)
                .build();
        MailCreatorService mailCreatorService = new MailCreatorService();
        Mail mail = mailCreatorService.buildSubscribeAlertMail(subscribe, airQualityIndex);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        mailSendingService.sendMailMessage(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
