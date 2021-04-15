package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {

    @Query(
            value = "SELECT * FROM markets m WHERE m.market_name = ?1 AND m.address = ?2",
            nativeQuery = true
    )
    Optional<Market> findWithNameAndAddress(String marketName, String address);
}
