package com.example.testlab.delegate.moderation;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SendNotificationRequestDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
//        try {
            log.info("Message was sent to another process");
            log.info("Current activity is " + delegateExecution.getCurrentActivityName());
            log.info("Current petition_id is " + delegateExecution.getVariable("petition_id"));
            delegateExecution
                    .getProcessEngineServices()
                    .getRuntimeService()
                    .createMessageCorrelation("mail-send-request")
                    .setVariable("petition_id", delegateExecution.getVariable("petition_id"))
                    .correlate();
//        } catch (Throwable throwable) {
//            throw new BpmnError("send-email-error", throwable.getMessage());
//        }
    }
}
