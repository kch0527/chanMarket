package com.example.market.entity.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentEditor {

    private String text;

    private String nowTime;

    @Builder
    public CommentEditor(String text, String nowTime) {
        this.text = text;
        this.nowTime = nowTime;
    }
}
