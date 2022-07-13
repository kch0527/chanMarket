package com.example.market.request.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class CommentEdit {

    @NotBlank(message = "text 없음")
    private String text;

    @NotBlank(message = "nowTime 없음")
    private String nowTime;

    @Builder
    public CommentEdit(String text, String nowTime) {
        this.text = text;
        this.nowTime = nowTime;
    }
}
