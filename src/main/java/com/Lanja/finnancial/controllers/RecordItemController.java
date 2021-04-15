package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.RecordItem;
import com.Lanja.finnancial.services.RecordItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/record/item")
public class RecordItemController {

    private final RecordItemService recordItemService;

    @Autowired
    public RecordItemController(RecordItemService recordItemService) {
        this.recordItemService = recordItemService;
    }

    @GetMapping(path = "/by/{recordDate}")
    public List<RecordItem> recordItemListByDateRecord(@PathVariable("recordDate") Date dateRecord) {
        return recordItemService.getRecordItemsByDateFinancialRecord(dateRecord);
    }

    @PostMapping(path = "/{recordId}/{sellId}")
    public void addNewRecordItem(@PathVariable("recordId") Integer recordId,
                                 @PathVariable("recordId") Integer sellId) {
        recordItemService.addNewRecordItem(recordId, sellId);
    }
}
