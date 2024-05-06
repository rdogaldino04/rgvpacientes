package com.galdino.rgvpacientes.service.movement;

import com.galdino.rgvpacientes.dto.MovementDTO;
import com.galdino.rgvpacientes.dto.MovementInput;
import com.galdino.rgvpacientes.dto.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.validation.MovementValidationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final List<MovementValidationStrategy> movementValidationStrategies;

    public MovementService(
            MovementRepository movementRepository,
            MovementMapper movementMapper,
            List<MovementValidationStrategy> movementValidationStrategies) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
        this.movementValidationStrategies = movementValidationStrategies;
    }

    public MovementDTO findById(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no movement with id %d", id)));
        return movementMapper.toDTO(movement);
    }

    @Transactional
    public MovementInput save(@Valid @NotNull MovementInput movementInput) throws EntityNotFoundException {
        Movement movement = movementMapper.toEntity(movementInput);
        this.movementValidationStrategies.forEach(validation -> validation.execute(movement));
        Movement movementSaved = this.movementRepository.save(movement);
        movementInput.setId(movementSaved.getId());
        return movementInput;
    }
}
