package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.RacialIdentity;

public interface RacialIdentityRepository extends JpaRepository<RacialIdentity, Long> {
}
