package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
