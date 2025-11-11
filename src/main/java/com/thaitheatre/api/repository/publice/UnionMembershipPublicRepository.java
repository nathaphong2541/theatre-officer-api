package com.thaitheatre.api.repository.publice;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.UnionMembership;

public interface UnionMembershipPublicRepository extends JpaRepository<UnionMembership, Long> {
    Page<UnionMembership> findByDelFlagAndRecordStatus(String delFlag, String recordStatus, Pageable pageable);
}