package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.PersonalIdentity;

public interface PersonalIdentityRepository extends JpaRepository<PersonalIdentity, Long> {
}
