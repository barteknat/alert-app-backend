package com.alert.app.backend.mail.service;

import com.alert.app.backend.domain.Statistics;
import com.alert.app.backend.mail.domain.Mail;
import com.alert.app.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.alert.app.backend.status.StatisticsStatus.SUBSCRIBE_E_MAIL_SENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSendingService {

    private final JavaMailSender javaMailSender;
    private final StatisticsService statisticsService;

    public void sendMailMessage(Mail mail) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMailMessage(mail));
            log.info("Email has been sent.");
            statisticsService.create(Statistics.builder()
                    .status(SUBSCRIBE_E_MAIL_SENT)
                    .remarks(mail.getMailTo())
                    .build());
        } catch (MailException e) {
            log.error("Failed to email sending process: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
