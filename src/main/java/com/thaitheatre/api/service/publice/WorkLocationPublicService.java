package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.WorkLocation;
import com.thaitheatre.api.model.publice.WorkLocationPublicDto;
import com.thaitheatre.api.repository.publice.WorkLocationPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class WorkLocationPublicService {
    private final WorkLocationPublicRepository repo;

    public ApiPage<WorkLocationPublicDto> list(int page, int size){
        Page<WorkLocation> p = repo.findByDelFlagAndRecordStatus("N","A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()), p.getTotalElements());
    }

    public WorkLocationPublicDto get(Long id){
        WorkLocation e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private WorkLocationPublicDto toDto(WorkLocation e){
        WorkLocationPublicDto d = new WorkLocationPublicDto();
        d.setId(e.getId()); d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        return d;
    }
}