package com.thaitheatre.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Page<Position> findByDepartment_Id(Long departmentId, Pageable pageable);
}
