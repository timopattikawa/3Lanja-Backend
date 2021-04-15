package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.FinancialRecord;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.FinancialRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinancialRecordServiceTest {

    @Mock  private FinancialRecordRepository financialRecordRepository;
    private FinancialRecordService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new FinancialRecordService(financialRecordRepository);
    }

    @Test
    void getAll() {
        serviceUnderTest.getAll();
        verify(financialRecordRepository).findAll();
    }

    @Test
    void itShouldPass_addNewRecord() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                null,
                null,
                4,
                null);
        serviceUnderTest.addNewRecord(financialRecord);
        ArgumentCaptor<FinancialRecord> financialRecordArgumentCaptor =
                ArgumentCaptor.forClass(FinancialRecord.class);

        verify(financialRecordRepository).save(financialRecordArgumentCaptor.capture());

        FinancialRecord financialRecordArgumentCaptorValue = financialRecordArgumentCaptor.getValue();
        assertThat(financialRecordArgumentCaptorValue).isEqualTo(financialRecord);
    }

    @Test
    void itShouldFail_foundDateRecord_addNewRecord() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                null,
                null,
                4,
                null);

        when(financialRecordRepository.findByRecordDate(date)).thenReturn(java.util.Optional.of(financialRecord));

        assertThatThrownBy(() -> serviceUnderTest.addNewRecord(financialRecord))
                .isInstanceOf(ApiBadRequestException.class)
                .hasMessageContaining("The Date for the record has been taken");
        verify(financialRecordRepository, never()).save(financialRecord);
    }

    @Test
    void itShouldSuccess_getById() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                null,
                null,
                4,
                null);
        serviceUnderTest.addNewRecord(financialRecord);
        ArgumentCaptor<FinancialRecord> financialRecordArgumentCaptor =
                ArgumentCaptor.forClass(FinancialRecord.class);
        verify(financialRecordRepository).save(financialRecordArgumentCaptor.capture());

        FinancialRecord financialRecordArgumentCaptorValue = financialRecordArgumentCaptor.getValue();

        when(financialRecordRepository.findById(financialRecordArgumentCaptorValue.getIdRecord()))
                .thenReturn(java.util.Optional.of(financialRecord));
        FinancialRecord byIdResult = serviceUnderTest.getById(financialRecordArgumentCaptorValue.getIdRecord());

        verify(financialRecordRepository).findById(financialRecordArgumentCaptorValue.getIdRecord());
        assertThat(byIdResult.getIdRecord()).isEqualTo(financialRecord.getIdRecord());
    }

    @Test
    void itShouldFail_getById() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                null,
                null,
                4,
                null);

        assertThatThrownBy(() -> serviceUnderTest.getById(financialRecord.getIdRecord()))
                .isInstanceOf(ApiNotFoundRequestException.class)
                .hasMessageContaining("Record Doesn't exist");
        verify(financialRecordRepository).findById(financialRecord.getIdRecord());
    }

    @Test
    void itShouldSuccess_deleteRecordByDate() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);
        when(financialRecordRepository.findByRecordDate(date))
                .thenReturn(java.util.Optional.of(financialRecord));
        serviceUnderTest.deleteRecordByDate(date);
        verify(financialRecordRepository).findByRecordDate(date);
    }

    @Test
    void itShouldFail_deleteRecordByDate() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);
        assertThatThrownBy(() -> serviceUnderTest.deleteRecordByDate(date))
                .isInstanceOf(ApiNotFoundRequestException.class)
                .hasMessageContaining("Record not found for delete");
        verify(financialRecordRepository).findByRecordDate(date);
    }

    @Test
    void itShouldSuccess_updateRecord() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);
        when(financialRecordRepository.findById(1))
                .thenReturn(java.util.Optional.of(financialRecord));

        String newRecordName = "Record Satu";
        Date newRecordDate = Date.valueOf("2021-04-06");
        serviceUnderTest.updateRecord(1, newRecordName, newRecordDate);
        verify(financialRecordRepository).findById(financialRecord.getIdRecord());
        verify(financialRecordRepository).findByRecordDate(newRecordDate);

        assertThat(financialRecord.getRecordName()).isEqualTo(newRecordName);
        assertThat(financialRecord.getRecordDate()).isEqualTo(newRecordDate);
    }

    @Test
    void itShouldFailUserNotFound_updateRecord() {
        String newRecordName = "Record Satu";
        Date newRecordDate = Date.valueOf("2021-04-06");
        assertThatThrownBy(() -> serviceUnderTest.updateRecord(1, newRecordName, newRecordDate))
                .isInstanceOf(ApiNotFoundRequestException.class)
                .hasMessageContaining("Record Doesn't exist");
        verify(financialRecordRepository).findById(1);
    }

    @Test
    void itShouldFailDateHasBeenUsed_updateRecord() {
        Date date = Date.valueOf("2021-04-05");
        FinancialRecord financialRecord = new FinancialRecord(
                1,
                "Record Pertama",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);

        Date date2 = Date.valueOf("2021-04-06");
        FinancialRecord financialRecord2 = new FinancialRecord(
                2,
                "Record kedua",
                date,
                date,
                Timestamp.valueOf(LocalDateTime.now()),
                4,
                null);

        when(financialRecordRepository.findById(1))
                .thenReturn(java.util.Optional.of(financialRecord));
        when(financialRecordRepository.findByRecordDate(date2))
                .thenReturn(java.util.Optional.of(financialRecord2));


        String newRecordName = "Record Satu";

        assertThatThrownBy(() -> serviceUnderTest.updateRecord(1, newRecordName, date2))
                .isInstanceOf(ApiBadRequestException.class)
                .hasMessageContaining("Date has been used");

        verify(financialRecordRepository).findById(financialRecord.getIdRecord());
        verify(financialRecordRepository).findByRecordDate(date2);

    }

}