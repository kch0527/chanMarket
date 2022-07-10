package com.example.market.entity.item;

import com.example.market.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemEditor {

    private String itemInformation;

    private String itemName;

    private String price;

    @Builder
    public ItemEditor(String itemInformation, String itemName, String price) {
        this.itemInformation = itemInformation;
        this.itemName = itemName;
        this.price = price;
    }
}
