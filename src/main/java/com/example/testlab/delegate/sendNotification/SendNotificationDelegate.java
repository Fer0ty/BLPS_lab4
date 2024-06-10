package com.example.testlab.delegate.sendNotification;

import com.example.testlab.entity.Petition;
import com.example.testlab.repository.PetitionRepository;
import com.example.testlab.schemas.petition.PetitionNotification;
import com.example.testlab.services.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SendNotificationDelegate implements JavaDelegate {
    final MailSenderService mailSenderService;
    final PetitionRepository petitionRepository;

    @Autowired
    public SendNotificationDelegate(MailSenderService mailSenderService, PetitionRepository petitionRepository) {
        this.mailSenderService = mailSenderService;
        this.petitionRepository = petitionRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long petition_id = (Long) delegateExecution.getVariable("petition_id");
        Petition petition = petitionRepository.findById(petition_id).orElseThrow(() -> new BpmnError("Ошибка в пересылке id."));

        PetitionNotification petitionNotification = new PetitionNotification();
        petitionNotification.setId(petition.getId());
        petitionNotification.setApproveStatus(petition.getApproveStatus());
        petitionNotification.setOwnerId(petition.getOwner().getId());
        mailSenderService.sendEmail(petitionNotification);
        log.info("Current activity is " + delegateExecution.getCurrentActivityName());
    }
}
