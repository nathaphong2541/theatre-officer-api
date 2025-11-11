package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.WorkLocation;
import com.thaitheatre.api.repository.WorkLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class WorkLocationService {

    private final WorkLocationRepository repo;

    public ApiPage<WorkLocationDto> list(int page, int size) {
        Page<WorkLocation> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                             p.getTotalElements());
    }
    public WorkLocationDto get(Long id) { return toDto(req(id)); }

    @Transactional
    public WorkLocationDto create(WorkLocationCreateUpdateDto in) {
        WorkLocation e = new WorkLocation();
        e.setNameTh(in.getNameTh()); e.setNameEn(in.getNameEn()); e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public WorkLocationDto update(Long id, WorkLocationCreateUpdateDto in) {
        WorkLocation e = req(id);
        e.setNameTh(in.getNameTh()); e.setNameEn(in.getNameEn()); e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }

    private WorkLocation req(Long id){ return repo.findById(id).orElseThrow(); }
    private WorkLocationDto toDto(WorkLocation e){
        WorkLocationDto d = new WorkLocationDto();
        d.setId(e.getId()); d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        return d;
    }
}
