package com.example.testlab.repository;

import com.example.testlab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long id);
    Optional<User> findByEmailAndPasswordHash(String email, String password);
    Boolean existsUserByEmail(String email);

}
