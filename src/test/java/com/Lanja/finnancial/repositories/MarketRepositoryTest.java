package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.Market;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MarketRepositoryTest {

    @Autowired
    private MarketRepository marketRepository;

    @AfterEach
    void tearDown() {
        marketRepository.deleteAll();
    }

    @Test
    void itShouldCheckExistEmailAndAddress() {
        String marketName = "Diana";
        String address = "Jln. Budiutomo";

        Market market = new Market(1, marketName, address, null);
        marketRepository.save(market);

        Optional<Market> expected = marketRepository.findWithNameAndAddress(marketName, address);
        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    void itShouldCheckNotExistEmailAndAddress() {
        String marketName = "Diana";
        String address = "Jln. Budiutomo";
        Optional<Market> expected = marketRepository.findWithNameAndAddress(marketName, address);
        assertThat(expected.isPresent()).isFalse();
    }

}