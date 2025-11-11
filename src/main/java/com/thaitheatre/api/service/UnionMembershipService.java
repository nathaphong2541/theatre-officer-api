package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.UnionMembership;
import com.thaitheatre.api.repository.UnionMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnionMembershipService {
    private final UnionMembershipRepository repo;

    public ApiPage<UnionMembershipDto> list(int page, int size) {
        Page<UnionMembership> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public UnionMembershipDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public UnionMembershipDto create(UnionMembershipCreateUpdateDto in) {
        UnionMembership e = new UnionMembership();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public UnionMembershipDto update(Long id, UnionMembershipCreateUpdateDto in) {
        UnionMembership e = req(id);
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private UnionMembership req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private UnionMembershipDto toDto(UnionMembership e) {
        UnionMembershipDto d = new UnionMembershipDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
