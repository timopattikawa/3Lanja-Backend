package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.FinancialRecord;
import com.Lanja.finnancial.services.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/records" )
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FinancialRecordController {

    private final FinancialRecordService financialRecordService;

    @Autowired
    public FinancialRecordController(FinancialRecordService financialRecordService) {
        this.financialRecordService = financialRecordService;
    }

    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return financialRecordService.getAll();
    }

    @GetMapping(path = "{recordId}")
    public FinancialRecord getRecord(@PathVariable("recordId") Integer recordId) {
        return financialRecordService.getById(recordId);
    }

    @PostMapping
    public void addNewRecords(@RequestBody FinancialRecord financialRecord) {
        financialRecordService.addNewRecord(financialRecord);
    }

    @PutMapping(path = "{recordId}")
    public void updateRecord(
            @PathVariable("recordId") Integer recordId,
            @RequestParam(required = false) String recordName,
            @RequestParam(required = false) Date recordDate
    ) {
        financialRecordService.updateRecord(recordId, recordName, recordDate);
    }

    @DeleteMapping(path = "{recordDate}")
    public void deleteRecord(@PathVariable("recordDate") Date recordDate) {
        financialRecordService.deleteRecordByDate(recordDate);
    }

}
