package com.thaitheatre.api.controller.publice;

// Controller
import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.publice.PartnerDirectoryPublicDto;
import com.thaitheatre.api.service.publice.PartnerDirectoryPublicService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/partner-directories")
@RequiredArgsConstructor
public class PartnerDirectoryPublicController {
    private final PartnerDirectoryPublicService svc;

    @GetMapping
    public ApiPage<PartnerDirectoryPublicDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public PartnerDirectoryPublicDto get(@PathVariable Long id) {
        return svc.get(id);
    }
}
