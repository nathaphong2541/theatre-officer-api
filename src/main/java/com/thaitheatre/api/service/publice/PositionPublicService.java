package com.thaitheatre.api.service.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.entity.Position;
import com.thaitheatre.api.model.publice.PositionPublicDto;
import com.thaitheatre.api.repository.publice.PositionPublicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PositionPublicService {
    private final PositionPublicRepository repo;

    public ApiPage<PositionPublicDto> list(Integer page, Integer size, Long departmentId){
        Pageable pg = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Position> p = (departmentId == null)
                ? repo.findByDelFlagAndRecordStatus("N","A", pg)
                : repo.findByDelFlagAndRecordStatusAndDepartment_Id("N","A", departmentId, pg);
        return new ApiPage<>(p.getContent().stream().map(this::toDto).collect(Collectors.toList()), p.getTotalElements());
    }

    public PositionPublicDto get(Long id){
        Position e = repo.findById(id).filter(x -> "N".equals(x.getDelFlag()) && "A".equals(x.getRecordStatus()))
                .orElseThrow();
        return toDto(e);
    }

    private PositionPublicDto toDto(Position e){
        PositionPublicDto d = new PositionPublicDto();
        d.setId(e.getId());
        d.setNameTh(e.getNameTh()); d.setNameEn(e.getNameEn()); d.setDescription(e.getDescription());
        d.setDepartmentId(e.getDepartment().getId());
        d.setDepartmentNameTh(e.getDepartment().getNameTh());
        d.setDepartmentNameEn(e.getDepartment().getNameEn());
        return d;
    }
}