package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.PartnerDirectory;

public interface PartnerDirectoryPublicRepository extends JpaRepository<PartnerDirectory, Long> {
    Page<PartnerDirectory> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}