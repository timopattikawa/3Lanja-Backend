package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.Market;
import com.Lanja.finnancial.services.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/market")
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping(path = "/all")
    public List<Market> getMarketList() {
        return marketService.getMarketList();
    }

    @PostMapping
    public void addMarket(@RequestBody Market market) {
        marketService.addMarket(market);
    }

    @DeleteMapping(path = "/{marketId}")
    public void deleteMarket(@PathVariable("marketId") Integer marketId) {

    }

}
