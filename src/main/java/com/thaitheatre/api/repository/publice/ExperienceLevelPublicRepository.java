package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.ExperienceLevel;

public interface ExperienceLevelPublicRepository extends JpaRepository<ExperienceLevel, Long> {
    Page<ExperienceLevel> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}