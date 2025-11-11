package com.thaitheatre.api.service.publice;

// Service
import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.RacialIdentity;
import com.thaitheatre.api.model.publice.RacialIdentityPublicDto;
import com.thaitheatre.api.repository.publice.RacialIdentityPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacialIdentityPublicService {
    private final RacialIdentityPublicRepository repo;

    public ApiPage<RacialIdentityPublicDto> list(int page, int size) {
        Page<RacialIdentity> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public RacialIdentityPublicDto get(Long id) {
        RacialIdentity e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private RacialIdentityPublicDto toDto(RacialIdentity e) {
        RacialIdentityPublicDto d = new RacialIdentityPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
