package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.service.movement.MovementService;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("api/movements")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("{id}")
    public MovementDTO findById(@Positive @PathVariable Long id) {
        return this.movementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovementIdDTO save(@Validated @RequestBody MovementInput movementInput) {
        return this.movementService.save(movementInput);
    }

    @GetMapping
    public PageWrapper<MovementDTO> getByFilter(@Valid MovementFilter movementFilter,
                                                @PageableDefault(size = 5) Pageable pageable) {
        return this.movementService.getByFilter(movementFilter, pageable);
    }

    @PutMapping("{id}")
    public MovementIdDTO update(@Positive @PathVariable Long id, @Validated @RequestBody MovementInput movementInput) {
        movementInput.setId(id);
        return this.movementService.update(id, movementInput);
    }

}
