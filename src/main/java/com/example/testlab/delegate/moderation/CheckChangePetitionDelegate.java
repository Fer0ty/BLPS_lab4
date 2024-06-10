package com.example.testlab.delegate.moderation;

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
public class CheckChangePetitionDelegate implements JavaDelegate {

    final PetitionRepository petitionRepository;

    @Autowired
    public CheckChangePetitionDelegate(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    private boolean isPresent(Long id){
        return petitionRepository.existsById((id));
    }

    private boolean isStatusCorrect(Long id){
        Petition petition = petitionRepository.findById(id).orElseThrow(() -> new BpmnError("Такого быть не должно"));
        return petition.getApproveStatus().equals("ON_HOLD");
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("petition_id");
        log.info("Получен id петиции: {}", id);
        if (isPresent(id)){
            if (!isStatusCorrect(id)){
                throw new BpmnError("Петиции с таким id не существует");
            }
        } else {
            throw new BpmnError("Петиции с таким id не существует");
        }
    }
}
