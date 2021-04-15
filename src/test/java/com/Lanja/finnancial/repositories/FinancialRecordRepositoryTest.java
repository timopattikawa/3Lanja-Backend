package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.FinancialRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FinancialRecordRepositoryTest {

    @Autowired
    private FinancialRecordRepository repositoryUnderTest;

    @AfterEach
    void tearDown() {
        repositoryUnderTest.deleteAll();
    }

    @Test
    void itShouldFindByRecordDate() {
        Date date = new Date(System.currentTimeMillis());
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);

        repositoryUnderTest.save(financialRecord);

        Optional<FinancialRecord> byRecordDate = repositoryUnderTest.findByRecordDate(date);
        assertThat(byRecordDate.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindByRecordDate() {
        Date date = new Date(System.currentTimeMillis());
        Date date1 = Date.valueOf("2021-04-03");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);

        repositoryUnderTest.save(financialRecord);

        Optional<FinancialRecord> byRecordDate = repositoryUnderTest.findByRecordDate(date1);
        assertThat(byRecordDate.isPresent()).isFalse();
    }
}