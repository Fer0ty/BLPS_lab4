package com.example.testlab.services;

import com.example.testlab.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;

    public void sendSimpleEmail(User user, String title, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);

        log.info("Отправлен email: \n\tTo: {}\n\tSubject: {}\n\tBody: {}", user.getEmail(), title, message);

    }

    public void sendSimpleEmail(String title, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("blpslab3artemiyandarina@gmail.com");
        simpleMailMessage.setTo("artemiy.soloviev@gmail.com");
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);


        log.info("Отправлен email: \n\tTo: {}\n\tSubject: {}\n\tBody: {}", "testemail", title, message);

    }
}
