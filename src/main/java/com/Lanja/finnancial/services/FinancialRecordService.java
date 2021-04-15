package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.FinancialRecord;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository financialRecordRepository;

    @Autowired
    public FinancialRecordService(FinancialRecordRepository financialRecordRepository) {
        this.financialRecordRepository = financialRecordRepository;
    }

    public List<FinancialRecord> getAll() {
        List<FinancialRecord> financialRecords = financialRecordRepository.findAll();
        return financialRecords;
    }

    public FinancialRecord getById(Integer recordId) {
        FinancialRecord financialRecord =
                financialRecordRepository.findById(recordId).orElseThrow(
                        () -> new ApiNotFoundRequestException("Record Doesn't exist")
                );
        return financialRecord;
    }

    public void addNewRecord(FinancialRecord record) {
        Optional<FinancialRecord> checkFinancialRecordDate
                = financialRecordRepository.findByRecordDate(record.getRecordDate());

        if(checkFinancialRecordDate.isPresent()) {
            throw new ApiBadRequestException("The Date for the record has been taken");
        }



        record.setLastUpdateDate();
        record.setCreateDate();
        financialRecordRepository.save(record);
    }

    @Transactional
    public void updateRecord(Integer recordId, String recordName, Date recordDate) {
        FinancialRecord financialRecord = financialRecordRepository
                .findById(recordId)
                .orElseThrow(() -> new ApiNotFoundRequestException("Record Doesn't exist"));

        if(!financialRecord.getRecordName().equals(recordName) && recordName != null && recordName.length() > 0) {
            financialRecord.setRecordName(recordName);
        }

        if(recordDate != null) {
            Optional<FinancialRecord> checkFinancialRecordByDate = financialRecordRepository
                    .findByRecordDate(recordDate);
            if(checkFinancialRecordByDate.isPresent()) {
                throw new ApiBadRequestException("Date has been used");
            }
            financialRecord.setRecordDate(recordDate);
        }
        financialRecord.setLastUpdateDate();
    }

    public void deleteRecordByDate(Date recordDate) {
        FinancialRecord financialRecord = financialRecordRepository.findByRecordDate(recordDate).orElseThrow(
                () -> new ApiNotFoundRequestException("Record not found for delete")
        );

        financialRecordRepository.delete(financialRecord);
    }
}
