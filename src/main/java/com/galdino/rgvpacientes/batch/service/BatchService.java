package com.galdino.rgvpacientes.batch.service;

import com.galdino.rgvpacientes.batch.dto.BatchFilter;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.batch.model.Batch;
import com.galdino.rgvpacientes.batch.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import com.galdino.rgvpacientes.repository.specs.BatchSpecs;
import com.galdino.rgvpacientes.service.ProductService;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductService productService;
    private final MovementItemRepository movementItemRepository;

    public BatchService(BatchRepository batchRepository, ProductService productService, MovementItemRepository movementItemRepository) {
        this.batchRepository = batchRepository;
        this.productService = productService;
        this.movementItemRepository = movementItemRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Batch create(Batch batch) {
        this.batchRepository.detach(batch);
        Optional<Batch> batchExists = this.batchRepository.findByBatchNumber(batch.getBatchNumber());
        if (batchExists.isPresent() && !batchExists.get().equals(batch)) {
            throw new BusinessException(String.format("There is already a batch with number %s", batch.getBatchNumber()));
        }
        this.productService.findById(batch.getProduct().getId());
        return this.batchRepository.save(batch);
    }

    public Batch findById(Long id) {
        return this.batchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no batch with id %d", id)));
    }

    public PageWrapper<Batch> findAll(BatchFilter batchFilter, Pageable pageable) {
        Page<Batch> batcPage = this.batchRepository.findAll(BatchSpecs.usingFilter(batchFilter), pageable);
        return new PageWrapper<>(batcPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (this.movementItemRepository.existsByBatch(new Batch(id))) {
            throw new BusinessException("Não é possível remover o lote, pois está vinculado ao um ou mais movimentações.");
        }
        this.batchRepository.deleteById(id);
    }
}
