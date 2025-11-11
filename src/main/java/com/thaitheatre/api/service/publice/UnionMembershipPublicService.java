package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.UnionMembership;
import com.thaitheatre.api.model.publice.UnionMembershipPublicDto;
import com.thaitheatre.api.repository.publice.UnionMembershipPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnionMembershipPublicService {
    private final UnionMembershipPublicRepository repo;

    public ApiPage<UnionMembershipPublicDto> list(int page, int size) {
        Page<UnionMembership> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public UnionMembershipPublicDto get(Long id) {
        UnionMembership e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private UnionMembershipPublicDto toDto(UnionMembership e) {
        UnionMembershipPublicDto d = new UnionMembershipPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}