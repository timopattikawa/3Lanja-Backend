package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.Market;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketService {

    private final MarketRepository marketRepository;

    @Autowired
    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<Market> getMarketList() {
        return marketRepository.findAll();
    }

    public void addMarket(Market market) {
        Optional<Market> withNameAndAddress = marketRepository.findWithNameAndAddress(market.getMarketName(), market.getAddress());

        if(withNameAndAddress.isPresent()) {
            throw new ApiBadRequestException("Market With Same Address has been added");
        }

        if(market.getMarketName() == null || market.getMarketName().length() <= 0) {
            throw new ApiBadRequestException("Market Name can't be null");
        }

        if(market.getAddress() == null || market.getAddress().length() <= 0) {
            throw new ApiBadRequestException("Market address can't be null");
        }

        marketRepository.save(market);
    }

    public void deleteMarket(Integer idMarket) {
        Market marketById = marketRepository.findById(idMarket).orElseThrow(
                () -> new ApiNotFoundRequestException("Market not found for delete.")
        );
        marketRepository.delete(marketById);
    }
}
