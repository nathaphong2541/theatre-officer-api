package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.Skill;
import com.thaitheatre.api.model.publice.SkillPublicDto;
import com.thaitheatre.api.repository.publice.SkillPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class SkillPublicService {
    private final SkillPublicRepository repo;

    public ApiPage<SkillPublicDto> list(Integer page, Integer size, Long positionId){
        Pageable pg = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Skill> p = (positionId == null)
                ? repo.findByDelFlagAndRecordStatus("N","A", pg)
                : repo.findByDelFlagAndRecordStatusAndPosition_Id("N","A", positionId, pg);
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()), p.getTotalElements());
    }

    public SkillPublicDto get(Long id){
        Skill e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private SkillPublicDto toDto(Skill e){
        SkillPublicDto d = new SkillPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        d.setPositionId(e.getPosition().getId());
        d.setPositionNameTh(e.getPosition().getNameTh());
        d.setPositionNameEn(e.getPosition().getNameEn());
        return d;
    }
}