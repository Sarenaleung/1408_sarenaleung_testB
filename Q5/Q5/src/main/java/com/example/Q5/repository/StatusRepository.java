package com.example.Q5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Q5.model.Status;

public interface StatusRepository extends JpaRepository<Status, String> {
    boolean existsByStatus(String status);
}
