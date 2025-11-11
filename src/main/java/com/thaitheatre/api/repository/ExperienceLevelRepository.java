package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.ExperienceLevel;

public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevel, Long> {
}