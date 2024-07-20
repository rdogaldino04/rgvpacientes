package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.enums.MovementName;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.service.movement.MovementService;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
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
    public ResponseEntity<MovementIdDTO> save(@Validated @RequestBody MovementInput movementInput) {
        MovementIdDTO movementIdDTO = processMovementInput(movementInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movementIdDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(movementIdDTO);
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

    private MovementIdDTO processMovementInput(MovementInput movementInput) {
        movementInput.setId(null);
        if (movementInput.getName() == MovementName.TRANSFERENCIA) {
            return new MovementIdDTO(this.movementService.transferStock(movementMapper.toEntity(movementInput)).getId());
        } else {
            return new MovementIdDTO(this.movementService.save(movementMapper.toEntity(movementInput)).getId());
        }
    }

}
