package com.thaitheatre.api.service.publice;

// Service
import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.GenderIdentity;
import com.thaitheatre.api.model.publice.GenderIdentityPublicDto;
import com.thaitheatre.api.repository.publice.GenderIdentityPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenderIdentityPublicService {
    private final GenderIdentityPublicRepository repo;

    public ApiPage<GenderIdentityPublicDto> list(int page, int size) {
        Page<GenderIdentity> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public GenderIdentityPublicDto get(Long id) {
        GenderIdentity e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private GenderIdentityPublicDto toDto(GenderIdentity e) {
        GenderIdentityPublicDto d = new GenderIdentityPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
