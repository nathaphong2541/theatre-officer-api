package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.WorkLocation;

public interface WorkLocationRepository extends JpaRepository<WorkLocation, Long> {
}
