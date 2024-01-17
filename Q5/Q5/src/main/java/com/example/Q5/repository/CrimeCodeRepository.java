package com.example.Q5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Q5.model.CrimeCode;

public interface CrimeCodeRepository extends JpaRepository<CrimeCode, Integer> {
}
