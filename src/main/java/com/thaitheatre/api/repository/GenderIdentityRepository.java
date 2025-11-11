package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.GenderIdentity;

public interface GenderIdentityRepository extends JpaRepository<GenderIdentity, Long> {
}
