package com.example.Q5.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Q5.model.CrimeRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface CrimeRecordRepository extends JpaRepository<CrimeRecord, Integer> {
    List<CrimeRecord> findByDateOccBetween(LocalDateTime startDate, LocalDateTime endDate);
}
