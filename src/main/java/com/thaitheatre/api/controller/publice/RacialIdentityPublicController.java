package com.thaitheatre.api.controller.publice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.publice.RacialIdentityPublicDto;
import com.thaitheatre.api.service.publice.RacialIdentityPublicService;

@RestController
@RequestMapping("/api/public/racial-identities")
@RequiredArgsConstructor
public class RacialIdentityPublicController {
    private final RacialIdentityPublicService svc;

    @GetMapping
    public ApiPage<RacialIdentityPublicDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public RacialIdentityPublicDto get(@PathVariable Long id) {
        return svc.get(id);
    }
}