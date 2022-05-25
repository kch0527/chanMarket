package com.example.market.service;

import com.example.market.entity.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    void deleteItem(Long itemId);
    void editItem(Long itemId, Item updateParam);
    Item readItem(Long itemId);
    List<Item> itemList();
}
