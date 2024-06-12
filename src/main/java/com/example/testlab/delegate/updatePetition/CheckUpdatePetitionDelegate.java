package com.example.testlab.delegate.updatePetition;


import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckUpdatePetitionDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String country = (String) delegateExecution.getVariable("update_petition_country");
        String title = (String) delegateExecution.getVariable("update_petition_title");
        String description = (String) delegateExecution.getVariable("update_petition_description");
        if (country.isBlank() || title.isBlank() || description.isBlank()){
            throw new BpmnError("Все поля формы должны быть заполнены");
        }
    }
}
