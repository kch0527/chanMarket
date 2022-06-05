package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.Member;
import com.example.market.repository.JpaBoardRepository;
import com.example.market.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

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
        boardRepository.deleteById(id);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    @Transactional
    public void updateView(Long boardId, Board board){
        Board updateBoard = boardRepository.findById(boardId).orElseThrow(()->
                new IllegalStateException("존재하지 않는 Board"));
        updateBoard.updateView(board.getCountView());
    }

}
