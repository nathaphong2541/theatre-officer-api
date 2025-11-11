// src/main/java/com/thaitheatre/api/service/PartnerDirectoryService.java
package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.model.entity.PartnerDirectory;
import com.thaitheatre.api.repository.PartnerDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PartnerDirectoryService {
    private final PartnerDirectoryRepository repo;

    public ApiPage<PartnerDirectoryDto> list(int page, int size){
        Page<PartnerDirectory> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()), p.getTotalElements());
    }
    public PartnerDirectoryDto get(Long id){ return toDto(req(id)); }

    @Transactional
    public PartnerDirectoryDto create(PartnerDirectoryCreateUpdateDto in){
        PartnerDirectory e = new PartnerDirectory();
        e.setNameTh(in.getNameTh()); e.setNameEn(in.getNameEn()); e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public PartnerDirectoryDto update(Long id, PartnerDirectoryCreateUpdateDto in){
        PartnerDirectory e = req(id);
        e.setNameTh(in.getNameTh()); e.setNameEn(in.getNameEn()); e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id){ repo.deleteById(id); }

    private PartnerDirectory req(Long id){ return repo.findById(id).orElseThrow(); }
    private PartnerDirectoryDto toDto(PartnerDirectory e){
        PartnerDirectoryDto d = new PartnerDirectoryDto();
        d.setId(e.getId()); d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        return d;
    }
}
