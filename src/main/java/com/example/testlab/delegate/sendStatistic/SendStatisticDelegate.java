package com.example.testlab.delegate.sendStatistic;

import com.example.testlab.services.EnterRecordService;
import com.example.testlab.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendStatisticDelegate implements JavaDelegate {
    private final MailService mailService;
    private final EnterRecordService enterRecordService;

    @Autowired
    public SendStatisticDelegate(MailService mailSenderService, EnterRecordService enterRecordService) {
        this.mailService = mailSenderService;
        this.enterRecordService = enterRecordService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        mailService.sendSimpleEmail("Статистка за прошлый месяц", enterRecordService.getMonthlyReport());
    }
}
