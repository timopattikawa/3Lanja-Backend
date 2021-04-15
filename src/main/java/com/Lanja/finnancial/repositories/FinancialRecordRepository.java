package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Integer> {
    Optional<FinancialRecord> findByRecordDate(Date dateRecord);
}
