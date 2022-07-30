package com.example.market.service.board;

import com.example.market.entity.Board;
import com.example.market.entity.member.Member;
import com.example.market.exception.ItemNotFound;
import com.example.market.repository.JpaBoardRepository;
import com.example.market.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final JpaBoardRepository boardRepository;
    @Override
    public Board createBoard(Board board, Member member) {
                board.setMember(member);
                board.setCountView(0L);
        boardRepository.save(board);
        return board;
    }
    public Board findBoard(Long id){
        return boardRepository.getById(id);
    }

    public void deleteBoard(Long id){
        boardRepository.delete(boardRepository.findById(id).orElseThrow(ItemNotFound::new));
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public void updateView(Long boardId, Board board){
        Board updateBoard = boardRepository.findById(boardId).orElseThrow(()->
                new IllegalStateException("존재하지 않는 Board"));
        updateBoard.updateView(board.getCountView());
    }

}
