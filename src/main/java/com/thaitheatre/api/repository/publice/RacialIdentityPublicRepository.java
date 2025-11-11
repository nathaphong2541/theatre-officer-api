package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.RacialIdentity;

public interface RacialIdentityPublicRepository extends JpaRepository<RacialIdentity, Long> {
    Page<RacialIdentity> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}