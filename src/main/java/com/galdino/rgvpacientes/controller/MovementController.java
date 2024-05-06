package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.MovementDTO;
import com.galdino.rgvpacientes.dto.MovementIdDTO;
import com.galdino.rgvpacientes.dto.MovementInput;
import com.galdino.rgvpacientes.service.movement.MovementService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("movements")
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

}
