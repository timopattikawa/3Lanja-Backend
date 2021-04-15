package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.Item;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getListItem() {
        return itemRepository.findAll();
    }

    public Item getItemById(Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiNotFoundRequestException("Item Not Found"));
    }

    public void addNewItem(Item item) {
        Optional<Item> byName = itemRepository.findByName(item.getItemName());
        if(byName.isPresent()) {
            throw new ApiBadRequestException("Item Has been added");
        }

        itemRepository.save(item);
    }

    public void deleteItem(Integer itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new ApiNotFoundRequestException("Item Not found")
        );
        itemRepository.delete(item);
    }
}
