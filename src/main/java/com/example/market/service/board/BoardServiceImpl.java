package com.example.market.service.board;

import com.example.market.entity.board.Board;
import com.example.market.entity.board.BoardEditor;
import com.example.market.entity.member.Member;
import com.example.market.exception.BoardNotFound;
import com.example.market.repository.JpaBoardRepository;
import com.example.market.request.board.BoardCreate;
import com.example.market.request.board.BoardEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final JpaBoardRepository boardRepository;
    @Override
    public Board createBoard(BoardCreate boardCreate, Member member, MultipartFile file) throws Exception{
        File imageFile = new File("C:\\Users\\chan\\images");
        imageFile.mkdir();

        String projectPath = System.getProperty("user.home") + "\\images"; //저장할 경로 지정
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

                Board board = Board.builder()
                        .member(member)
                        .title(boardCreate.getTitle())
                        .price(boardCreate.getPrice())
                        .itemInformation(boardCreate.getItemInformation())
                        .category(boardCreate.getCategory())
                        .countView(0L)
                        .filename(fileName)
                        .filepath("C:\\Users\\chan\\images\\" + fileName)
                        .build();
        boardRepository.save(board);
        return board;
    }
    public Board findBoard(Long id){
        return boardRepository.getById(id);
    }

    public void deleteBoard(Long id){
        boardRepository.delete(boardRepository.findById(id).orElseThrow(BoardNotFound::new));
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public void updateView(Long boardId, Board board){
        Board updateBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFound::new);
        updateBoard.updateView(board.getCountView());
    }

    @Transactional
    public void editBoard(Long boardId ,BoardEdit updateParam){
        Board board = findBoard(boardId);
        BoardEditor.BoardEditorBuilder boardEditorBuilder = board.toEditor();
        BoardEditor boardEditor = boardEditorBuilder
                .title(updateParam.getTitle())
                .price(updateParam.getPrice())
                .itemInformation(updateParam.getItemInformation())
                .category(updateParam.getCategory())
                .build();
        board.edit(boardEditor);
    }

    public Page<Board> boardSearchList(String keyword, String category,Pageable pageable){
        return boardRepository.findByTitleContainingAndCategoryContaining(keyword, category, pageable);
    }

}
