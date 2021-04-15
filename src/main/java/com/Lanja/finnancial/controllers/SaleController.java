package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.Sale;
import com.Lanja.finnancial.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(path = "/all")
    public List<Sale> getAllSale() {
        return saleService.getSales();
    }

    @PostMapping
    public void createSale(@RequestBody  Sale sale){
        saleService.createSale(sale);
    }

}
