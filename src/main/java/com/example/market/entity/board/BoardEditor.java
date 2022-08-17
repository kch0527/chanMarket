package com.example.market.entity.board;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardEditor {

    private String title;
    private String price;
    private String itemInformation;
    private String category;

    @Builder
    public BoardEditor(String title, String price, String itemInformation, String category) {
        this.title = title;
        this.price = price;
        this.itemInformation = itemInformation;
        this.category = category;
    }
}
