package com.thaitheatre.api.controller;

import com.thaitheatre.api.common.ApiPage;
import com.thaitheatre.api.model.dto.*;
import com.thaitheatre.api.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService svc;

    @GetMapping
    public ApiPage<PositionDto> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return svc.list(page, size);
    }

    @GetMapping("/{id}")
    public PositionDto get(@PathVariable Long id) {
        return svc.get(id);
    }

    @PostMapping
    public PositionDto create(@Valid @RequestBody PositionCreateUpdateDto in) {
        return svc.create(in);
    }

    @PutMapping("/{id}")
    public PositionDto update(@PathVariable Long id,
            @Valid @RequestBody PositionCreateUpdateDto in) {
        return svc.update(id, in);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
