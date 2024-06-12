package com.example.testlab.repository;

import com.example.testlab.entity.EnterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EnterRecordRepository extends JpaRepository<EnterRecord, Long> {
    List<EnterRecord> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);
}

