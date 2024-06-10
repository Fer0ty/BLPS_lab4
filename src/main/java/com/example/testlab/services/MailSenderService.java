package com.example.testlab.services;

import com.example.testlab.exceptions.NotFoundException;
import com.example.testlab.repository.UserRepository;
import com.example.testlab.schemas.petition.PetitionNotification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class MailSenderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;

    public void sendEmail(PetitionNotification petitionNotification) {
        String title = "Обновление статуса петиции";
        String message = String.format("Статус вашей петиции с id: %s обновлен\n\tНовый статус петиции: %s", petitionNotification.getId(), petitionNotification.getApproveStatus());
        mailService.sendSimpleEmail(userRepository.findById(petitionNotification.getOwnerId()).orElseThrow(() -> new NotFoundException(petitionNotification.getOwnerId(), "User")), title, message);
    }
}
