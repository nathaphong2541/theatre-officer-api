package com.thaitheatre.api.service.publice;

// Service
import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.ExperienceLevel;
import com.thaitheatre.api.model.publice.ExperienceLevelPublicDto;
import com.thaitheatre.api.repository.publice.ExperienceLevelPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceLevelPublicService {
    private final ExperienceLevelPublicRepository repo;

    public ApiPage<ExperienceLevelPublicDto> list(int page, int size) {
        Page<ExperienceLevel> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public ExperienceLevelPublicDto get(Long id) {
        ExperienceLevel e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private ExperienceLevelPublicDto toDto(ExperienceLevel e) {
        ExperienceLevelPublicDto d = new ExperienceLevelPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
