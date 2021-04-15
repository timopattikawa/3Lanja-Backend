package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.RecordItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordItemRepository extends JpaRepository<RecordItem, Integer> {
    @Query(
            value = "SELECT * FROM record_items ri WHERE ri.id_record = (SELECT r.id_record FROM records r WHERE r.record_date = ?1)",
            nativeQuery = true
    )
    List<RecordItem> recordItemsByDateFinancialRecord(Date recordDate);
}
