package com.example.market.request.item;

import com.example.market.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class ItemEdit {

    @NotBlank(message = "itemInformation 없음")
    private String itemInformation;

    @NotBlank(message = "itemName 없음")
    private String itemName;

    @NotBlank(message = "price 없음")
    private String price;

    @Builder
    public ItemEdit(String itemInformation, String itemName, String price) {
        this.itemInformation = itemInformation;
        this.itemName = itemName;
        this.price = price;
    }
}
