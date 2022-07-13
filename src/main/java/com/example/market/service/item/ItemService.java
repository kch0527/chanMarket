package com.example.market.service.item;

import com.example.market.entity.item.Item;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.item.ItemEdit;

import java.util.List;

public interface ItemService {
    Item addItem(ItemCreate itemCreate);
    void editItem(Long itemId, ItemEdit updateParam);
    Item readItem(Long itemId);
    List<Item> itemList();
}
