package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.enums.MovementName;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.service.movement.MovementService;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("api/movements")
public class MovementController {

    private final MovementService movementService;
    private final MovementMapper movementMapper;

    public MovementController(MovementService movementService, MovementMapper movementMapper) {
        this.movementService = movementService;
        this.movementMapper = movementMapper;
    }

    @GetMapping("{id}")
    public MovementDTO findById(@Positive @PathVariable Long id) {
        return this.movementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovementIdDTO save(@Validated @RequestBody MovementInput movementInput) {
        movementInput.setId(null);
        if (movementInput.getName() == MovementName.TRANSFERENCIA) {
            return this.movementService.transferStock(movementMapper.toEntity(movementInput));
        }
        return new MovementIdDTO(this.movementService.save(movementMapper.toEntity(movementInput)).getId());
    }

    @GetMapping
    public PageWrapper<MovementDTO> getByFilter(@Valid MovementFilter movementFilter,
                                                @PageableDefault(size = 5) Pageable pageable) {
        return this.movementService.getByFilter(movementFilter, pageable);
    }

    @PutMapping("{id}")
    public MovementIdDTO update(@Positive @PathVariable Long id, @Validated @RequestBody MovementInput movementInput) {
        movementInput.setId(id);
        return this.movementService.update(id, movementMapper.updateEntity(movementInput));
    }

    @GetMapping("/{id}/items")
    public List<MovementItemDTO> findByMovementId(@Positive @PathVariable Long id) {
        return this.movementService.findByMovementId(id);
    }

}
