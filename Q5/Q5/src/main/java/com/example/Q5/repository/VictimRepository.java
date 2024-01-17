package com.example.Q5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Q5.model.Victim;

public interface VictimRepository extends JpaRepository<Victim, Integer> {
}
