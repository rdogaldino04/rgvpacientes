package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.batch.BatchDTO;
import com.galdino.rgvpacientes.dto.batch.BatchFilter;
import com.galdino.rgvpacientes.dto.batch.BatchInput;
import com.galdino.rgvpacientes.util.wrapper.PageWrapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.service.BatchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/batchies")
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

    @GetMapping()
    public PageWrapper<Batch> findAll(BatchFilter batchFilter, @PageableDefault(size = 5) Pageable pageable) {
        return this.batchService.findAll(batchFilter, pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.batchService.delete(id);
    }

}
