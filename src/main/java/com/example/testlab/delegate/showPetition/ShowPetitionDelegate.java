package com.example.testlab.delegate.showPetition;

import com.example.testlab.entity.Petition;
import com.example.testlab.repository.PetitionRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowPetitionDelegate implements JavaDelegate {
    final PetitionRepository petitionRepository;

    @Autowired
    public ShowPetitionDelegate(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Petition petition = petitionRepository.findById((Long) delegateExecution.getVariable("petition_id")).orElseThrow(()-> new BpmnError("Петиции с таким id не существует"));
        delegateExecution.setVariable("creation_date", petition.getCreationDate().toString());
        delegateExecution.setVariable("country", petition.getCountry());
        delegateExecution.setVariable("title", petition.getTitle());
        delegateExecution.setVariable("description", petition.getDescription());
        delegateExecution.setVariable("owner_id", petition.getOwner().getId());
    }
}
