package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.PersonalIdentity;
import com.thaitheatre.api.model.publice.PersonalIdentityPublicDto;
import com.thaitheatre.api.repository.publice.PersonalIdentityPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalIdentityPublicService {
    private final PersonalIdentityPublicRepository repo;

    public ApiPage<PersonalIdentityPublicDto> list(int page, int size) {
        Page<PersonalIdentity> p = repo.findByDelFlagAndRecordStatus("N", "A",
                PageRequest.of(page, size, Sort.by("id").descending()));
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                p.getTotalElements());
    }

    public PersonalIdentityPublicDto get(Long id) {
        PersonalIdentity e = repo.findById(id)
                .filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus())).orElseThrow();
        return toDto(e);
    }

    private PersonalIdentityPublicDto toDto(PersonalIdentity e) {
        PersonalIdentityPublicDto d = new PersonalIdentityPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh());
        d.setNameEn(e.getNameEn());
        d.setDescription(e.getDescription());
        return d;
    }
}