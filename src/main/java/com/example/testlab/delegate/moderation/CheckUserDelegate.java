package com.example.testlab.delegate.moderation;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckUserDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
            if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("moders")) {
                throw new BpmnError("This user does not have access to this feature.");
            }
    }
}
