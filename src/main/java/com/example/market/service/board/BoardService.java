package com.example.market.service.board;

import com.example.market.entity.board.Board;
import com.example.market.entity.member.Member;
import com.example.market.request.board.BoardCreate;
import com.example.market.request.board.BoardEdit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Board createBoard(BoardCreate boardCreate, Member member);
    Board findBoard(Long id);
    void deleteBoard(Long id);
    Page<Board> boardList(Pageable pageable);
    void updateView(Long boardId, Board board);
    void editBoard(Long boardId , BoardEdit updateParam);
    Page<Board> boardSearchList(String keyword, String category, Pageable pageable);

}
