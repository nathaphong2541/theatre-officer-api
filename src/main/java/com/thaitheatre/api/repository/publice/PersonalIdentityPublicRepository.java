package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.PersonalIdentity;

public interface PersonalIdentityPublicRepository extends JpaRepository<PersonalIdentity, Long> {
    Page<PersonalIdentity> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}