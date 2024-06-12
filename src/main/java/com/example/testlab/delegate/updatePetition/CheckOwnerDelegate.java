package com.example.testlab.delegate.updatePetition;

import com.example.testlab.entity.Petition;
import com.example.testlab.repository.PetitionRepository;
import com.example.testlab.repository.UserRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOwnerDelegate implements JavaDelegate {
    final PetitionRepository petitionRepository;
    final UserRepository userRepository;

    @Autowired
    public CheckOwnerDelegate(PetitionRepository petitionRepository, UserRepository userRepository) {
        this.petitionRepository = petitionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Petition petition = petitionRepository.findById((Long)delegateExecution.getVariable("petition_id")).orElseThrow(() -> new BpmnError("Петиции с таким id не существует"));
        Long owner_id = petition.getOwner().getId();
        if (owner_id != (Long) delegateExecution.getVariable("id")){
            throw new BpmnError("Вы не являетесь владельцем петиции");
        }
        delegateExecution.setVariable("update_petition_country", petition.getCountry());
        delegateExecution.setVariable("update_petition_title", petition.getTitle());
        delegateExecution.setVariable("update_petition_description", petition.getDescription());

    }
}
