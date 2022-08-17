package com.example.market.response;

import com.example.market.entity.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponse {
    private Long id;

    public BoardResponse(Board board) {
        this.id = board.getId();
    }

    @Builder
    public BoardResponse(Long id) {
        this.id = id;
    }
}
