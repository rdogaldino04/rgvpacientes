package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.dto.stock.StockFilter;
import com.galdino.rgvpacientes.dto.stock.StockSaveDTO;
import com.galdino.rgvpacientes.mapper.StockMapper;
import com.galdino.rgvpacientes.model.Stock;
import com.galdino.rgvpacientes.service.StockService;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/stocks")
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockController(StockService stockService, StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockDTO save(@RequestBody @Valid StockSaveDTO stockSaveDTO) {
        return this.stockMapper.toDTO(this.stockService.save(this.stockMapper.toEntity(stockSaveDTO)));
    }

    @PutMapping("{id}")
    public StockDTO update(@PathVariable Long id, @RequestBody @Valid StockSaveDTO stockSaveDTO) {
        Stock stock = this.stockMapper.toEntity(stockSaveDTO);
        stock.setId(id);
        return this.stockMapper.toDTO(this.stockService.update(stock));
    }

    @GetMapping("{id}")
    public StockDTO findById(@PathVariable Long id) {
        return this.stockMapper.toDTO(this.stockService.findById(id));
    }

    @GetMapping
    public PageWrapper<Stock> getStockByFilter(StockFilter stockFilter, Pageable pageable) {
        return this.stockService.getStockByFilter(stockFilter, pageable);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.stockService.delete(id);
    }
}
