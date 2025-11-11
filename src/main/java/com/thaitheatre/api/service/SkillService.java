package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.Position;
import com.thaitheatre.api.model.entity.Skill;
import com.thaitheatre.api.repository.PositionRepository;
import com.thaitheatre.api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository repo;
    private final PositionRepository positionRepo;

    public ApiPage<SkillDto> list(int page, int size) {
        Page<Skill> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(
                p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public SkillDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public SkillDto create(SkillCreateUpdateDto in) {
        Position pos = positionRepo.findById(in.getPositionId()).orElseThrow();
        Skill e = new Skill();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        e.setPosition(pos);
        return toDto(repo.save(e));
    }

    @Transactional
    public SkillDto update(Long id, SkillCreateUpdateDto in) {
        Skill e = req(id);
        Position pos = positionRepo.findById(in.getPositionId()).orElseThrow();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        e.setPosition(pos);
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private Skill req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private SkillDto toDto(Skill e) {
        SkillDto dto = new SkillDto();
        dto.setId(e.getId());
                dto.setNameTh(e.getNameTh());
        dto.setNameEn(e.getNameEn());
        dto.setDescription(e.getDescription());
        dto.setPositionId(e.getPosition().getId());
        dto.setPositionNameTh(e.getPosition().getNameTh());
        dto.setPositionNameEn(e.getPosition().getNameEn());
        return dto;
    }
}
