package com.thaitheatre.api.model.dto;

import lombok.Data;

@Data
public class UnionMembershipDto {
    private Long id;
    private String nameTh;
    private String nameEn;
    private String description;
}