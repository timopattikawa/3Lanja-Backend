package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.Market;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.MarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @Mock private MarketRepository marketRepository;
    private MarketService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new MarketService(marketRepository);
    }

    @Test
    void passTestGetMarketList() {
        serviceUnderTest.getMarketList();
        verify(marketRepository).findAll();
    }

    @Test
    void canAddMarket() {
        Market market = new Market(
                1,
                "Diana",
                "Jln. Budiutomo",
                null
        );

        serviceUnderTest.addMarket(market);

        ArgumentCaptor<Market> argumentCaptorMarket =
                ArgumentCaptor.forClass(Market.class);

        verify(marketRepository).save(argumentCaptorMarket.capture());

        Market marketArgumentValue = argumentCaptorMarket.getValue();

        assertThat(marketArgumentValue).isEqualTo(market);
    }

    @Test
    void canNotAddMarket_throwNameAndAddressIsTaken() {
        Market market = new Market(
                1,
                "Diana",
                "Jln. Budiutomo",
                null
        );

        when(marketRepository.findWithNameAndAddress(market.getMarketName(), market.getAddress()))
                .thenReturn(Optional.of(market));

        assertThatThrownBy(() -> serviceUnderTest.addMarket(market))
                .isInstanceOf(ApiBadRequestException.class).hasMessageContaining("Market With Same Address has been added");
        verify(marketRepository, never()).save(market);
    }

    @Test
    void canNotAddMarket_nameIsEmptyOrNull() {
        Market market = new Market(
                1,
                "",
                "Jln. Budiutomo",
                null
        );
        assertThatThrownBy(() -> serviceUnderTest.addMarket(market))
                .isInstanceOf(ApiBadRequestException.class)
                .hasMessageContaining("Market Name can't be null");

        verify(marketRepository, never()).save(market);
    }

    @Test
    void canNotAddMarket_addressIsEmptyOrNull() {
        Market market = new Market(
                1,
                "Diana",
                "",
                null
        );
        assertThatThrownBy(() -> serviceUnderTest.addMarket(market))
                .isInstanceOf(ApiBadRequestException.class)
                .hasMessageContaining("Market address can't be null");
    }

    @Test
    void itShouldSuccess_deleteMarket() {
        Market market = new Market(
                1,
                "Diana",
                "",
                null
        );
        when(marketRepository.findById(1)).thenReturn(Optional.of(market));
        serviceUnderTest.deleteMarket(market.getIdMarket());
        verify(marketRepository).delete(market);
    }

    @Test
    void itShouldFailNotFoundId_deleteMarket() {
        Market market = new Market(
                1,
                "Diana",
                "",
                null
        );
        assertThatThrownBy(() -> serviceUnderTest.deleteMarket(market.getIdMarket()))
                .isInstanceOf(ApiNotFoundRequestException.class)
                .hasMessageContaining("Market not found for delete.");
        verify(marketRepository, never()).delete(market);
    }
}