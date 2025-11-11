package com.thaitheatre.api.controller;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService svc;

    @GetMapping
    public ApiPage<DepartmentDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public DepartmentDto get(@PathVariable Long id) {
        return svc.get(id);
    }

    @PostMapping
    public DepartmentDto create(@Valid @RequestBody DepartmentCreateUpdateDto in) {
        return svc.create(in);
    }

    @PutMapping("/{id}")
    public DepartmentDto update(@PathVariable Long id,
            @Valid @RequestBody DepartmentCreateUpdateDto in) {
        return svc.update(id, in);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
