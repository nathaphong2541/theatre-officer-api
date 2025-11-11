package com.thaitheatre.api.controller.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.publice.DepartmentPublicDto;
import com.thaitheatre.api.service.publice.DepartmentPublicService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/departments")
@RequiredArgsConstructor
public class DepartmentPublicController {
    private final DepartmentPublicService svc;

    @GetMapping
    public ApiPage<DepartmentPublicDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public DepartmentPublicDto get(@PathVariable Long id) {
        return svc.get(id);
    }
}