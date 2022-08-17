package com.example.market.request.board;

import com.example.market.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class BoardEdit {
    @NotBlank(message = "title 없음")
    private String title;
    @NotBlank(message = "price 없음")
    private String price;
    @NotBlank(message = "itemInformation 없음")
    private String itemInformation;
    @NotBlank(message = "category 없음")
    private String category;

    @Builder
    public BoardEdit(String title, String price, String itemInformation, String category) {
        this.title = title;
        this.price = price;
        this.itemInformation = itemInformation;
        this.category = category;
    }
}
