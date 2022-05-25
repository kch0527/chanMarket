package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.Member;
import com.example.market.repository.JpaBoardRepository;
import com.example.market.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final JpaBoardRepository boardRepository;
    @Override
    public Board createBoard(Board board, Member member) {
        board.setMember(member);
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

}
