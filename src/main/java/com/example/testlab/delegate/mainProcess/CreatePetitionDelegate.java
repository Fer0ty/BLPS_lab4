package com.example.testlab.delegate.mainProcess;

import com.example.testlab.entity.Petition;
import com.example.testlab.exceptions.NotFoundException;
import com.example.testlab.repository.PetitionRepository;
import com.example.testlab.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CreatePetitionDelegate implements JavaDelegate {

    final PetitionRepository petitionRepository;
    final UserRepository userRepository;

    @Autowired
    public CreatePetitionDelegate(PetitionRepository petitionRepository, UserRepository userRepository) {
        this.petitionRepository = petitionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String country = (String) delegateExecution.getVariable("create_petition_country");
        String title = (String) delegateExecution.getVariable("create_petition_title");
        String description = (String) delegateExecution.getVariable("create_petition_description");
        Long owner_id = (Long) delegateExecution.getVariable("id");
        Petition newPetition = new Petition();
        newPetition.setCountry(country);
        newPetition.setTitle(title);
        newPetition.setDescription(description);
        newPetition.setOwner(userRepository.findUserById(owner_id).orElseThrow(() -> new NotFoundException(owner_id, "Ошибка :(")));
        petitionRepository.save(newPetition);
    }
}
