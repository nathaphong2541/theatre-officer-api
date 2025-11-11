package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.WorkLocation;

public interface WorkLocationPublicRepository extends JpaRepository<WorkLocation, Long> {
    Page<WorkLocation> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}