package com.thaitheatre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thaitheatre.api.model.entity.UnionMembership;

public interface UnionMembershipRepository extends JpaRepository<UnionMembership, Long> {
}
