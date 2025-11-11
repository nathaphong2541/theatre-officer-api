package com.thaitheatre.api.service;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.PersonalIdentity;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.repository.PersonalIdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalIdentityService {
    private final PersonalIdentityRepository repo;

    public ApiPage<PersonalIdentityDto> list(int page, int size) {
        Page<PersonalIdentity> p = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public PersonalIdentityDto get(Long id) {
        return toDto(req(id));
    }

    @Transactional
    public PersonalIdentityDto create(PersonalIdentityCreateUpdateDto in) {
        PersonalIdentity e = new PersonalIdentity();
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public PersonalIdentityDto update(Long id, PersonalIdentityCreateUpdateDto in) {
        PersonalIdentity e = req(id);
        e.setNameTh(in.getNameTh());
        e.setNameEn(in.getNameEn());
        e.setDescription(in.getDescription());
        return toDto(repo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private PersonalIdentity req(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private PersonalIdentityDto toDto(PersonalIdentity e) {
        PersonalIdentityDto d = new PersonalIdentityDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}
