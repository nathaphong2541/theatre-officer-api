package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
