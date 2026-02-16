package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
