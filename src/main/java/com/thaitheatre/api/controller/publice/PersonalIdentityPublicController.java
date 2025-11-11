package com.thaitheatre.api.controller.publice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.publice.PersonalIdentityPublicDto;
import com.thaitheatre.api.service.publice.PersonalIdentityPublicService;

@RestController
@RequestMapping("/api/public/personal-identities")
@RequiredArgsConstructor
public class PersonalIdentityPublicController {
    private final PersonalIdentityPublicService svc;

    @GetMapping
    public ApiPage<PersonalIdentityPublicDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public PersonalIdentityPublicDto get(@PathVariable Long id) {
        return svc.get(id);
    }
}