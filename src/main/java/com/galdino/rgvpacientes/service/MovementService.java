package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.MaterialMovementItemInput;
import com.galdino.rgvpacientes.dto.MovementInput;
import com.galdino.rgvpacientes.dto.MovementItemInput;
import com.galdino.rgvpacientes.dto.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final PatientService patientService;
    private final CompanyService companyService;
    private final SectorService sectorService;
    private final StockService stockService;
    private final MaterialService materialService;

    public MovementService(
            MovementRepository movementRepository,
            MovementMapper movementMapper,
            PatientService patientService,
            CompanyService companyService,
            SectorService sectorService,
            StockService stockService,
            MaterialService materialService) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
        this.patientService = patientService;
        this.companyService = companyService;
        this.sectorService = sectorService;
        this.stockService = stockService;
        this.materialService = materialService;
    }

    public Movement findById(Long id) {
        return movementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no movement with id %d", id)));
    }

    @Transactional
    public MovementInput save(@Valid @NotNull MovementInput movementInput) throws EntityNotFoundException {
        StringBuilder messageError = new StringBuilder();
        try {
            if (!this.patientService.existsById(movementInput.getPatient().getId())) {
                messageError.append(String.format("[There is no patient with id %d]-", movementInput.getPatient().getId()));
            }

            if (!this.companyService.existsById(movementInput.getCompany().getId())) {
                messageError.append(String.format("[There is no company with id %d]-", movementInput.getCompany().getId()));
            }

            if (!this.sectorService.existsById(movementInput.getSector().getId())) {
                messageError.append(String.format("[There is no sector with id %d]-", movementInput.getSector().getId()));
            }

            if (!this.stockService.existsById(movementInput.getStock().getId())) {
                messageError.append(String.format("[There is no stock with id %d]-", movementInput.getStock().getId()));
            }

            movementInput.getItems().forEach(itemInput -> {
                if (!this.materialService.existsById(itemInput.getMaterial().getId())) {
                    messageError.append(String.format("[There is no material with id %d]-", itemInput.getMaterial().getId()));
                }
            });

            if (StringUtils.hasText(messageError.toString())) {
                throw new EntityNotFoundException(
                        Arrays.stream(messageError.toString().split("-"))
                                .reduce("", (acc, error) -> acc + error)
                );
            }
            Movement movementSaved = this.movementRepository.save(movementMapper.toEntity(movementInput));
            movementInput.setId(movementSaved.getId());
            List<MovementItemInput> itemsInputs = movementSaved.getItems().stream().map(item -> {
                        MovementItemInput itemInput = new MovementItemInput();
                        itemInput.setId(item.getId());
                        itemInput.setAmount(item.getAmount());
                        MaterialMovementItemInput materialMovementItemInput = new MaterialMovementItemInput();
                        materialMovementItemInput.setId(item.getMaterial().getId());
                        materialMovementItemInput.setName(item.getMaterial().getName());
                        itemInput.setMaterial(materialMovementItemInput);
                        return itemInput;
                    })
                    .collect(Collectors.toList());
            movementInput.setItems(itemsInputs);
            return movementInput;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Error when saving movement");
        }
    }
}
