package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.dto.product.ProductInput;
import com.galdino.rgvpacientes.mapper.ProductMapper;
import com.galdino.rgvpacientes.model.Product;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.ProductRepository;
import com.galdino.rgvpacientes.service.movement.MovementItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;
    private ProductMapper productMapper;
    private MovementItemService movementItemService;
    private ProductRepository productRepository;
    private EntityManager manager;
    private BatchRepository batchRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productMapper = mock(ProductMapper.class);
        movementItemService = mock(MovementItemService.class);
        manager = mock(EntityManager.class);
        batchRepository = mock(BatchRepository.class);
        productService = new ProductService(productRepository, batchRepository);
        ReflectionTestUtils.setField(productService, "manager", manager);
    }

    @Test
    void testExistsById() {
        when(productRepository.existsById(1L)).thenReturn(true);
        boolean existsById = productService.existsById(1L);
        assert (existsById);
    }

    @Test
    void testCreate() {
        ProductInput productInput = new ProductInput();
        productInput.setName("Product");
        when(productRepository.existsByName("Product")).thenReturn(false);

        Product product = new Product();
        product.setName("Product");
        when(productMapper.toEntity(productInput)).thenReturn(product);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product productSave = invocation.getArgument(0);
            productSave.setId(1L);
            productSave.setName("Product");
            return productSave;
        });

        when(productMapper.toDTO(product)).thenReturn(ProductDTO.builder().id(1L).name("Product").build());
        ProductDTO productDTO = this.productMapper.toDTO(productService.create(this.productMapper.toEntity(productInput)));
        assert (productDTO.getId() == 1L);
    }

}
