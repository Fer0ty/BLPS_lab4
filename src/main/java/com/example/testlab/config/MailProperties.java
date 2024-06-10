package com.example.testlab.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "application.mail")
public class MailProperties {
    // Выводит в консоль текст сообщения
    private Boolean log = true;
    // Отключает отправку сообщения
    private Boolean disable = false;
}