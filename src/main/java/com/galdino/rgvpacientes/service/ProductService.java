package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.mapper.ProductMapper;
import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.dto.product.ProductFilter;
import com.galdino.rgvpacientes.dto.product.ProductInput;
import com.galdino.rgvpacientes.dto.wrapper.PageWrapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.model.Product;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.ProductRepository;
import com.galdino.rgvpacientes.service.exception.BusinessException;
import com.galdino.rgvpacientes.service.exception.EntityInUseException;
import com.galdino.rgvpacientes.service.movement.MovementItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class ProductService {

    public static final String THERE_IS_NO_PRODUCT_WITH_CODE = "There is no product with code %d";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MovementItemService movementItemService;
    private final BatchRepository batchRepository;

    @PersistenceContext
    private EntityManager manager;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            MovementItemService movementItemService,
            BatchRepository batchRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.movementItemService = movementItemService;
        this.batchRepository = batchRepository;
    }

    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }

    public ProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, id)));
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Transactional
    public ProductDTO create(ProductInput productInput) {
        if (productRepository.existsByName(productInput.getName())) {
            throw new BusinessException(
                    String.format("Product with the name %s already exists", productInput.getName()));
        }
        Product product = productRepository.save(this.productMapper.toEntity(productInput));
        return this.productMapper.toDTO(product);
    }

    public PageWrapper<ProductDTO> getProductByFilter(@Valid ProductFilter productFilter, Pageable pageable) {
        Page<ProductDTO> page = this.productRepository.getProductByFilter(productFilter, pageable);
        return new PageWrapper<>(page);
    }

    @Transactional
    public ProductDTO update(Long id, ProductInput productInput) {
        return this.productRepository.findById(id)
                .map(productFound -> {
                    productFound.setName(productInput.getName());

                    manager.detach(productFound);
                    Optional<Product> existingProduct = productRepository.findByName(productInput.getName());
                    if (existingProduct.isPresent() && !existingProduct.get().equals(productFound)) {
                        throw new BusinessException(
                                String.format("Product with the name %s already exists", productInput.getName()));
                    }

                    return productMapper.toDTO(productRepository.save(productFound));
                }).orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, id)));
    }

    @Transactional
    public void delete(Long id) {
        if (this.movementItemService.existsByBatch(new Batch(id))) {
            throw new EntityInUseException(String.format("Product with code %d is in use and cannot be removed.", id));
        }
        Product productExists = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, id)));
        if (batchRepository.existsByProduct_Id(productExists.getId())) {
            throw new EntityNotFoundException(String.format("Não é possível remover o produto %s, pois está vinculado ao um ou mais lotes.", productExists.getName()));
        }
        productRepository.delete(
                productRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, id))));
    }

}
