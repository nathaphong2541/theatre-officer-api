package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.Department;
import com.thaitheatre.api.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repo;

    public ApiPage<DepartmentDto> list(int page, int size) {
        Page<Department> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(
                p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public DepartmentDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public DepartmentDto create(DepartmentCreateUpdateDto in) {
        Department e = new Department();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public DepartmentDto update(Long id, DepartmentCreateUpdateDto in) {
        Department e = req(id);
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // helpers
    private Department req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private DepartmentDto toDto(Department e) {
        DepartmentDto d = new DepartmentDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
