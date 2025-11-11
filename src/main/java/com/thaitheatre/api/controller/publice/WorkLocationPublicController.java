package com.thaitheatre.api.controller.publice;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.publice.WorkLocationPublicDto;
import com.thaitheatre.api.service.publice.WorkLocationPublicService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/work-locations")
@RequiredArgsConstructor
public class WorkLocationPublicController {
    private final WorkLocationPublicService svc;

    @GetMapping
    public ApiPage<WorkLocationPublicDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public WorkLocationPublicDto get(@PathVariable Long id) {
        return svc.get(id);
    }
}