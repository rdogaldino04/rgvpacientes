package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.repository.MaterialRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("materials")
public class MaterialController {

    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @GetMapping
    public List<Material> getAll(String name) {
        return this.materialRepository.getAll(name);
    }

    @GetMapping("{id}")
    public Material getAll(@PathVariable Long id) {
        return this.materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no material with code %d", id)));
    }

}
