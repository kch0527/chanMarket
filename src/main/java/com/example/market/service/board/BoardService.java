package com.example.market.service.board;

import com.example.market.entity.Board;
import com.example.market.entity.member.Member;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board, Member member);
    Board findBoard(Long id);
    void deleteBoard(Long id);
    List<Board> boardList();
    void updateView(Long boardId, Board board);

}
