package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.dto.product.ProductFilter;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.model.Product;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.ProductRepository;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private final BatchRepository batchRepository;

    @PersistenceContext
    private EntityManager manager;

    public ProductService(
            ProductRepository productRepository,
            BatchRepository batchRepository) {
        this.productRepository = productRepository;
        this.batchRepository = batchRepository;
    }

    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }

    @Cacheable("product")
    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, id)));
    }

    @Transactional
    public Product create(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new BusinessException(
                    String.format("Product with the name %s already exists", product.getName()));
        }
        return productRepository.save(product);
    }

    public PageWrapper<ProductDTO> getProductByFilter(@Valid ProductFilter productFilter, Pageable pageable) {
        Page<ProductDTO> page = this.productRepository.getProductByFilter(productFilter, pageable);
        return new PageWrapper<>(page);
    }

    @CachePut(value = "product", key = "#product.id")
    @Transactional
    public Product update(Product product) {
        return this.productRepository.findById(product.getId())
                .map(productFound -> {
                    productFound.setName(product.getName());
                    manager.detach(productFound);
                    Optional<Product> existingProduct = productRepository.findByName(product.getName());
                    if (existingProduct.isPresent() && !existingProduct.get().equals(productFound)) {
                        throw new BusinessException(
                                String.format("Product with the name %s already exists", product.getName()));
                    }

                    return productRepository.save(productFound);
                }).orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_PRODUCT_WITH_CODE, product.getId())));
    }

    @CacheEvict(value = "product", key = "#id")
    @Transactional
    public void delete(Long id) {
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
