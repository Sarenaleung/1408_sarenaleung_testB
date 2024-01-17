package com.example.Q5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Q5.model.Weapon;


public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
}
