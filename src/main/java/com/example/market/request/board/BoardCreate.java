package com.example.market.request.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class BoardCreate {
    @NotBlank(message = "title 없음")
    private String title;
    @NotBlank(message = "price 없음")
    private String price;
    @NotBlank(message = "itemInformation 없음")
    private String itemInformation;
    @NotBlank(message = "category 없음")
    @Column(length = 20000)
    private String category;


    @Builder
    public BoardCreate(String title, String price, String itemInformation, String category) {
        this.title = title;
        this.price = price;
        this.itemInformation = itemInformation;
        this.category = category;
    }
}
