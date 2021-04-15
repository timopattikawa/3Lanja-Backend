package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.Item;
import com.Lanja.finnancial.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/all")
    public List<Item> getListItem() {
        return itemService.getListItem();
    }

    @GetMapping(path = "{itemId}")
    public Item getItemById(@PathVariable("itemId") Integer itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addNewItem(item);
    }

    @DeleteMapping(path = "/{itemId}")
    public void deleteItem(@PathVariable("itemId") Integer itemId) {
        itemService.deleteItem(itemId);
    }

}
