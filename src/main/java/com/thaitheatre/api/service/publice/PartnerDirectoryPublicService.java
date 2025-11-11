package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.PartnerDirectory;
import com.thaitheatre.api.model.publice.PartnerDirectoryPublicDto;
import com.thaitheatre.api.repository.publice.PartnerDirectoryPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerDirectoryPublicService {
    private final PartnerDirectoryPublicRepository repo;

    public ApiPage<PartnerDirectoryPublicDto> list(int page, int size) {
        Page<PartnerDirectory> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public PartnerDirectoryPublicDto get(Long id) {
        PartnerDirectory e = repo.findById(id)
                .filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus())).orElseThrow();
        return toDto(e);
    }

    private PartnerDirectoryPublicDto toDto(PartnerDirectory e) {
        PartnerDirectoryPublicDto d = new PartnerDirectoryPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}