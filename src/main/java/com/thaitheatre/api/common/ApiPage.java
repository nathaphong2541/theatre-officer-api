package com.thaitheatre.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiPage<T> {
    private List<T> items;
    private long total;
}
