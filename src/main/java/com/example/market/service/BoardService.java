package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {
    Board createBoard(Board board, Member member);
    Board findBoard(Long id);
    void deleteBoard(Long id);
    List<Board> boardList();
    void updateView(Long boardId, Board board);

}
