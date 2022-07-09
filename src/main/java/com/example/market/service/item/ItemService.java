package com.example.market.service.item;

import com.example.market.entity.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    void deleteItem(Long id);
    void editItem(Long itemId, Item updateParam);
    Item readItem(Long itemId);
    List<Item> itemList();
}
