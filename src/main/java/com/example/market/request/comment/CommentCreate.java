package com.example.market.request.comment;

import com.example.market.entity.Board;
import com.example.market.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class CommentCreate {

    @NotBlank(message = "member 없음")
    private Member member;

    @NotBlank(message = "board 없음")
    private Board board;

    @NotBlank(message = "text 없음")
    private String text;

    @NotBlank(message = "nowTime 없음")
    private String nowTime;

    @Builder
    public CommentCreate(Member member, Board board, String text, String nowTime) {
        this.member = member;
        this.board = board;
        this.text = text;
        this.nowTime = nowTime;
    }
}
