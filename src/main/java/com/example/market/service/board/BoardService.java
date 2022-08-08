package com.example.market.service.board;

import com.example.market.entity.Board;
import com.example.market.entity.member.Member;
import com.example.market.response.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board, Member member);
    Board findBoard(Long id);
    void deleteBoard(Long id);
    Page<Board> boardList(Pageable pageable);
    void updateView(Long boardId, Board board);

}
