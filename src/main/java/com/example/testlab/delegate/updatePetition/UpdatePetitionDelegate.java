package com.example.testlab.delegate.updatePetition;

import com.example.testlab.entity.ApproveStatus;
import com.example.testlab.entity.Petition;
import com.example.testlab.repository.PetitionRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetitionDelegate implements JavaDelegate {
    final PetitionRepository petitionRepository;

    @Autowired
    public UpdatePetitionDelegate(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Petition updatedPetition = new Petition();
        Petition petition = petitionRepository.findById((Long) delegateExecution.getVariable("petition_id")).orElseThrow(() -> new BpmnError("Такого не должно быть)"));
        updatedPetition.setCountry((String) delegateExecution.getVariable("update_petition_country"));
        updatedPetition.setTitle((String) delegateExecution.getVariable("update_petition_title"));
        updatedPetition.setDescription((String) delegateExecution.getVariable("update_petition_description"));
        updatedPetition.setOwner(petition.getOwner());
        updatedPetition.setApproveStatus(ApproveStatus.ON_HOLD.toString());
        updatedPetition.setId(petition.getId());
        petitionRepository.save(updatedPetition);
    }
}
