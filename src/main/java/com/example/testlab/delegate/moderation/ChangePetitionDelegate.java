package com.example.testlab.delegate.moderation;

import com.example.testlab.entity.ApproveStatus;
import com.example.testlab.entity.Petition;
import com.example.testlab.repository.PetitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ChangePetitionDelegate implements JavaDelegate {

    final PetitionRepository petitionRepository;

    @Autowired
    public ChangePetitionDelegate(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("petition_id");
        String newApproveStatus = (String) delegateExecution.getVariable("new_status");
        if (newApproveStatus.equals(ApproveStatus.CONFIRMED.toString()) || newApproveStatus.equals(ApproveStatus.REJECTED.toString())) {
            Petition petition = petitionRepository.findById(id).orElseThrow(() -> new BpmnError("Такого быть не должно"));
            petition.setApproveStatus(newApproveStatus);
            petitionRepository.save(petition);
        } else {
            throw new BpmnError("Нельзя выставить такой статус.");
        }


    }
}
