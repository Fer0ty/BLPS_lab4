package com.example.testlab.repository;

import com.example.testlab.entity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
    Optional<Petition> findById(Long id);
}
