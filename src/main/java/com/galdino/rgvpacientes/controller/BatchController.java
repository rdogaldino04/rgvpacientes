package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.service.BatchService;

@RestController
@RequestMapping("batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BatchDTO create(@RequestBody @Valid BatchInput batchInput) {
        return this.batchService.create(batchInput);
    }

    @PutMapping("/{id}")
    public BatchDTO update(@PathVariable Long id, @RequestBody @Valid BatchInput batchInput) {
        batchInput.setId(id);
        return this.batchService.create(batchInput);
    }

    @GetMapping("/{id}")
    public BatchDTO findById(@PathVariable Long id) {
        return this.batchService.findById(id);
    }

}
