package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.service.MaterialService;

@RestController
@RequestMapping("materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public Page<MaterialDTO> getAll(MaterialDTO materialDTO,
            @Valid @Positive @PageableDefault(size = 5) Pageable pageable) {
        return this.materialService.getAll(materialDTO, pageable);
    }

    @GetMapping("{id}")
    public MaterialDTO findById(@PathVariable Long id) {
        return this.materialService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MaterialDTO create(@RequestBody @Valid @NotNull MaterialInput materialInput) {
        return this.materialService.create(materialInput);
    }

    @PutMapping("/{id}")
    public MaterialDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull MaterialInput materialInput) {
        return materialService.update(id, materialInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        materialService.delete(id);
    }

}
