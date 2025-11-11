package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.Department;
import com.thaitheatre.api.model.entity.Position;
import com.thaitheatre.api.repository.DepartmentRepository;
import com.thaitheatre.api.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository repo;
    private final DepartmentRepository deptRepo;

    public ApiPage<PositionDto> list(int page, int size) {
        Page<Position> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(
                p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public PositionDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public PositionDto create(PositionCreateUpdateDto in) {
        Department dep = deptRepo.findById(in.getDepartmentId()).orElseThrow();
        Position e = new Position();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        e.setDepartment(dep);
        return toDto(repo.save(e));
    }

    @Transactional
    public PositionDto update(Long id, PositionCreateUpdateDto in) {
        Position e = req(id);
        Department d = deptRepo.findById(in.getDepartmentId()).orElseThrow();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        e.setDepartment(d);
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private Position req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private PositionDto toDto(Position e) {
        PositionDto dto = new PositionDto();
        dto.setId(e.getId());
        dto.setNameTh(e.getNameTh());
        dto.setNameEn(e.getNameEn());
        dto.setDescription(e.getDescription());
        dto.setDepartmentId(e.getDepartment().getId());
        dto.setDepartmentNameTh(e.getDepartment().getNameTh());
        dto.setDepartmentNameEn(e.getDepartment().getNameEn());
        return dto;
    }
}
