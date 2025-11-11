package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.RacialIdentity;
import com.thaitheatre.api.repository.RacialIdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacialIdentityService {
    private final RacialIdentityRepository repo;

    public ApiPage<RacialIdentityDto> list(int page, int size) {
        Page<RacialIdentity> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public RacialIdentityDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public RacialIdentityDto create(RacialIdentityCreateUpdateDto in) {
        RacialIdentity e = new RacialIdentity();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public RacialIdentityDto update(Long id, RacialIdentityCreateUpdateDto in) {
        RacialIdentity e = req(id);
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private RacialIdentity req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private RacialIdentityDto toDto(RacialIdentity e) {
        RacialIdentityDto d = new RacialIdentityDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
