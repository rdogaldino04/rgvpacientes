package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<MaterialDTO> getAll(MaterialDTO materialDTO) {
        return this.materialService.getAll(materialDTO);
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
