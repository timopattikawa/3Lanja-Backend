package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.FinancialRecord;
import com.Lanja.finnancial.entity.RecordItem;
import com.Lanja.finnancial.entity.Sale;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.FinancialRecordRepository;
import com.Lanja.finnancial.repositories.RecordItemRepository;
import com.Lanja.finnancial.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RecordItemService {

    private final RecordItemRepository recordItemRepository;
    private final FinancialRecordRepository financialRecordRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public RecordItemService(RecordItemRepository recordItemRepository,
                             FinancialRecordRepository financialRecordRepository,
                             SaleRepository saleRepository) {
        this.recordItemRepository = recordItemRepository;
        this.financialRecordRepository = financialRecordRepository;
        this.saleRepository = saleRepository;
    }

    public List<RecordItem> getRecordItemsByDateFinancialRecord(Date recordDate) {
        List<RecordItem> recordItems = recordItemRepository.recordItemsByDateFinancialRecord(recordDate);
        if(recordItems.isEmpty()) {
            throw new ApiNotFoundRequestException("Not found ");
        }
        return recordItems;
    }

    public void addNewRecordItem(Integer recordId, Integer saleId) {
        FinancialRecord financialRecordById = financialRecordRepository.findById(recordId)
                .orElseThrow(
                        () -> new ApiNotFoundRequestException("The financial record not found"));
        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new ApiNotFoundRequestException("Sell Id not found"));
        RecordItem recordItem = new RecordItem();
        recordItem.setFinancialRecord(financialRecordById);
        recordItem.setSale(sale);
        recordItem.setCreatedDate(Date.valueOf(LocalDate.now()));

        recordItemRepository.save(recordItem);
    }
}
