package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT * FROM items i WHERE i.item_name = ?1", nativeQuery = true)
    Optional<Item> findByName(String itemName);

}
