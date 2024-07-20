package com.galdino.rgvpacientes.domain.product.controller;

import com.galdino.rgvpacientes.domain.product.mapper.ProductMapper;
import com.galdino.rgvpacientes.domain.product.service.ProductService;
import com.galdino.rgvpacientes.domain.product.model.Product;
import com.galdino.rgvpacientes.domain.product.product.ProductDTO;
import com.galdino.rgvpacientes.domain.product.product.ProductFilter;
import com.galdino.rgvpacientes.domain.product.product.ProductInput;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public PageWrapper<ProductDTO> getProductByFilter(@Valid ProductFilter productFilter,
                                                      @PageableDefault(size = 5) Pageable pageable) {
        return this.productService.getProductByFilter(productFilter, pageable);
    }

    @GetMapping("{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productMapper.toDTO(productService.findById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid @NotNull ProductInput productInput) {
        return productMapper.toDTO(productService.create(productMapper.toEntity(productInput)));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable @NotNull @Positive Long id,
                             @RequestBody @Valid @NotNull ProductInput productInput) {
        Product product = productMapper.toEntity(productInput);
        product.setId(id);
        return productMapper.toDTO(productService.update(product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        productService.delete(id);
    }

}
