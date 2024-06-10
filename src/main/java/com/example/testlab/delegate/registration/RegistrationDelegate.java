package com.example.testlab.delegate.registration;

import com.example.testlab.entity.User;
import com.example.testlab.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegistrationDelegate implements JavaDelegate {

    final UserRepository userRepository;

    @Autowired
    public RegistrationDelegate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("user_email");
        String password = (String) execution.getVariable("user_password");
        if (userRepository.existsUserByEmail(email)) {
            throw new BpmnError("email_already_in_use", "Пользователь с email %s уже существует".formatted(email));
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }
}
