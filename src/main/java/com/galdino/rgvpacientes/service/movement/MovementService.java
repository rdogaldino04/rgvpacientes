package com.galdino.rgvpacientes.service.movement;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.enums.MovementName;
import com.galdino.rgvpacientes.enums.MovementType;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.validation.MovementValidationStrategy;
import com.galdino.rgvpacientes.shared.security.SecurityUtils;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementItemService movementItemService;
    private final MovementMapper movementMapper;
    private final List<MovementValidationStrategy> movementValidationStrategies;
    private final Validator validator;
    @Autowired
    private ApplicationContext applicationContext;

    public MovementService(
            MovementRepository movementRepository,
            MovementItemService movementItemService,
            MovementMapper movementMapper,
            List<MovementValidationStrategy> movementValidationStrategies, Validator validator) {
        this.movementRepository = movementRepository;
        this.movementItemService = movementItemService;
        this.movementMapper = movementMapper;
        this.movementValidationStrategies = movementValidationStrategies;
        this.validator = validator;
    }

    public MovementDTO findById(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no movement with id %d", id)));
        return movementMapper.toDTO(movement);
    }

    @Transactional
    public Movement save(@Valid @NotNull Movement movement) throws EntityNotFoundException {
        Set<ConstraintViolation<Movement>> violations = this.validator.validate(movement);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (!movement.hasItems()) {
            throw new EntityNotFoundException("The movement must have at least one item");
        }

        if (movement.getName().equals(MovementName.ENTRADA_AVULSA) || movement.getName().equals(MovementName.SAIDA_AVULSA)) {
            movement.setStockDestinationLocation(null);
        }
        movement.validateNoDuplicateBatchIds();
        this.movementValidationStrategies.forEach(validation -> validation.execute(movement));
        movement.setUser(SecurityUtils.getUserCurrent());
        return this.movementRepository.save(movement);
    }

    public PageWrapper<MovementDTO> getByFilter(MovementFilter movementFilter, Pageable pageable) {
        Page<MovementDTO> movements = this.movementRepository.getByFilter(movementFilter, pageable);
        return new PageWrapper<>(movements);
    }

    @Transactional
    public MovementIdDTO update(Long id, @Valid @NotNull Movement movement) {
        if (!this.movementRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("There is no movement with id %d", id));
        }

        if (!movement.hasItems()) {
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

    @Transactional
    public Movement transferStock(Movement movement) {
        MovementService proxy = applicationContext.getBean(MovementService.class);

        if (!movement.getName().equals(MovementName.TRANSFERENCIA)) {
            throw new BusinessException("Movement name must be TRANSFERENCIA for transfer movements");
        }
        if (movement.getStockDestinationLocation() == null) {
            throw new BusinessException("Stock destination location is required for transfer movements");
        }
        if (movement.getStockSourceLocation().getId().equals(movement.getStockDestinationLocation().getId())) {
            throw new BusinessException("Stock source location and stock destination location must be different");
        }

        movement.setMovementType(MovementType.OUTPUT);
        Movement savedOutputMovement = proxy.save(movement);

        Movement inputMovement = Movement.builder()
                .relatedMovement(savedOutputMovement)
                .patient(movement.getPatient())
                .stockSourceLocation(movement.getStockDestinationLocation())
                .stockDestinationLocation(movement.getStockSourceLocation())
                .items(movement.getItems())
                .movementType(MovementType.INPUT)
                .name(MovementName.TRANSFERENCIA)
                .build();
        proxy.save(inputMovement);

        savedOutputMovement.setRelatedMovement(inputMovement);
        return proxy.save(savedOutputMovement);
    }
}
