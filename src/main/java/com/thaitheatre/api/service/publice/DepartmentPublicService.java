package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.Department;
import com.thaitheatre.api.model.publice.DepartmentPublicDto;
import com.thaitheatre.api.repository.publice.DepartmentPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DepartmentPublicService {
    private final DepartmentPublicRepository repo;

    public ApiPage<DepartmentPublicDto> list(int page, int size){
        Page<Department> p = repo.findByDelFlagAndRecordStatus("N","A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(
                p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public DepartmentPublicDto get(Long id){
        Department e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private DepartmentPublicDto toDto(Department e){
        DepartmentPublicDto d = new DepartmentPublicDto();
        d.setId(e.getId()); d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        return d;
    }
}