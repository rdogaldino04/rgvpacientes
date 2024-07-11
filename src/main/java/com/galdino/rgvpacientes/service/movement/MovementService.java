package com.galdino.rgvpacientes.service.movement;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.validation.MovementValidationStrategy;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementItemService movementItemService;
    private final MovementMapper movementMapper;
    private final List<MovementValidationStrategy> movementValidationStrategies;

    public MovementService(
            MovementRepository movementRepository,
            MovementItemService movementItemService,
            MovementMapper movementMapper,
            List<MovementValidationStrategy> movementValidationStrategies) {
        this.movementRepository = movementRepository;
        this.movementItemService = movementItemService;
        this.movementMapper = movementMapper;
        this.movementValidationStrategies = movementValidationStrategies;
    }

    public MovementDTO findById(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no movement with id %d", id)));
        return movementMapper.toDTO(movement);
    }

    @Transactional
    public MovementIdDTO save(@Valid @NotNull MovementInput movementInput) throws EntityNotFoundException {
        movementInput.setId(null);
        Movement movement = movementMapper.toEntity(movementInput);
        if (movement.getItems().isEmpty()) {
            throw new EntityNotFoundException("The movement must have at least one item");
        }
        movement.validateNoDuplicateBatchIds();
        this.movementValidationStrategies.forEach(validation -> validation.execute(movement));
        Movement movementSaved = this.movementRepository.save(movement);
        return new MovementIdDTO(movementSaved.getId());
    }

    public PageWrapper<MovementDTO> getByFilter(MovementFilter movementFilter, Pageable pageable) {
        Page<MovementDTO> movements = this.movementRepository.getByFilter(movementFilter, pageable);
        return new PageWrapper<>(movements);
    }

    @Transactional
    public MovementIdDTO update(Long id, @Valid @NotNull MovementInput movementInput) {
        if (!this.movementRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("There is no movement with id %d", id));
        }
        Movement movement = movementMapper.updateEntity(movementInput);
        if (movement.getItems().isEmpty()) {
            throw new EntityNotFoundException("The movement must have at least one item");
        }
        movement.validateNoDuplicateBatchIds();
        this.movementValidationStrategies.forEach(validation -> validation.execute(movement));
        Movement movementSaved = this.movementRepository.save(movement);
        return new MovementIdDTO(movementSaved.getId());
    }

    public List<MovementItemDTO> findByMovementId(Long id) {
        return this.movementItemService.findByMovementId(id).stream()
                .map(movementItem -> new MovementItemDTO(
                        movementItem.getId(),
                        movementItem.getBatch(),
                        movementItem.getQuantity(),
                        movementItem.getMovementItemDate()))
                .collect(Collectors.toList());
    }
}
