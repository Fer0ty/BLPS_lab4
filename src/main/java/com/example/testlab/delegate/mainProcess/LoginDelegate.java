package com.example.testlab.delegate.mainProcess;

import com.example.testlab.entity.User;
import com.example.testlab.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginDelegate implements JavaDelegate {
    final UserRepository userRepository;

    @Autowired
    public LoginDelegate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");
        String password = (String) delegateExecution.getVariable("password");
        User user = userRepository.findByEmailAndPasswordHash(email, password).orElseThrow(() -> new BpmnError("Неправильный email или пароль"));
        delegateExecution.setVariable("id", user.getId());
        log.info("LoginDelegate: id = {}", delegateExecution.getVariable("id"));
    }
}
