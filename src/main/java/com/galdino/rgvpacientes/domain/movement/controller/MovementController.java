package com.galdino.rgvpacientes.domain.movement.controller;

import com.galdino.rgvpacientes.domain.movement.dto.MovementFilter;
import com.galdino.rgvpacientes.domain.movement.dto.MovementIdDTO;
import com.galdino.rgvpacientes.domain.movement.dto.MovementInput;
import com.galdino.rgvpacientes.domain.movement.enums.MovementName;
import com.galdino.rgvpacientes.domain.movement.mapper.MovementMapper;
import com.galdino.rgvpacientes.domain.movement.mapper.assembler.MovementModelAssembler;
import com.galdino.rgvpacientes.domain.movement.mapper.model.MovementModel;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import com.galdino.rgvpacientes.domain.movement.service.MovementService;
import com.galdino.rgvpacientes.domain.movementitem.mapper.MovementItemModel;
import com.galdino.rgvpacientes.domain.movementitem.mapper.MovementItemModelAssembler;
import com.galdino.rgvpacientes.shared.data.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("api/movements")
public class MovementController {

    private final MovementService movementService;
    private final MovementMapper movementMapper;
    private final MovementModelAssembler movementModelAssembler;
    private final PagedResourcesAssembler<Movement> pagedResourcesAssembler;
    private final MovementItemModelAssembler movementItemModelAssembler;

    public MovementController(MovementService movementService, MovementMapper movementMapper, MovementModelAssembler movementModelAssembler, PagedResourcesAssembler<Movement> pagedResourcesAssembler, MovementItemModelAssembler movementItemModelAssembler) {
        this.movementService = movementService;
        this.movementMapper = movementMapper;
        this.movementModelAssembler = movementModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.movementItemModelAssembler = movementItemModelAssembler;
    }

    @GetMapping("{id}")
    public MovementModel findById(@Positive @PathVariable Long id) {
        return movementModelAssembler.toModel(movementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovementModel> save(@Validated @RequestBody MovementInput movementInput) {
        MovementModel movementModel = processMovementInput(movementInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movementModel.getId())
                .toUri();

        return ResponseEntity.created(uri).body(movementModel);
    }

    @GetMapping
    public PagedModel<MovementModel> getByFilter(@Valid MovementFilter movementFilter,
                                                 @PageableDefault(size = 5) Pageable pageable) {
        Page<Movement> movementsPage = this.movementService.getByFilter(movementFilter, pageable);
        movementsPage = new PageWrapper<>(movementsPage, pageable);
        return pagedResourcesAssembler.toModel(movementsPage, movementModelAssembler);
    }

    @PutMapping("{id}")
    public MovementIdDTO update(@Positive @PathVariable Long id, @Validated @RequestBody MovementInput movementInput) {
        movementInput.setId(id);
        return this.movementService.update(id, movementMapper.updateEntity(movementInput));
    }

    @GetMapping("/{id}/items")
    public CollectionModel<MovementItemModel> findByMovementId(@Positive @PathVariable Long id) {
        return movementItemModelAssembler.toCollectionModel(this.movementService.findByMovementId(id));
    }

    private MovementModel processMovementInput(MovementInput movementInput) {
        movementInput.setId(null);
        if (movementInput.getName() == MovementName.TRANSFERENCIA) {
            return movementModelAssembler.toModel(this.movementService.transferStock(movementMapper.toEntity(movementInput)));
        } else {
            return movementModelAssembler.toModel(this.movementService.save(movementMapper.toEntity(movementInput)));
        }
    }

}
