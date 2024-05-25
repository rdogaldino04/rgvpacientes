package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.dto.product.ProductFilter;
import com.galdino.rgvpacientes.dto.product.ProductInput;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import com.galdino.rgvpacientes.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PageWrapper<ProductDTO> getProductByFilter(@Valid ProductFilter productFilter,
            @PageableDefault(size = 5) Pageable pageable) {
        return this.productService.getProductByFilter(productFilter, pageable);
    }

    @GetMapping("{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid @NotNull ProductInput productInput) {
        return this.productService.create(productInput);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull ProductInput productInput) {
        return productService.update(id, productInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        productService.delete(id);
    }

}
