package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.service.basket.BasketService;
import com.example.market.service.board.BoardService;
import com.example.market.service.comment.CommentService;
import com.example.market.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class BoardController {

    private final BoardService boardService;
    private final ItemService itemService;
    private final CommentService commentService;

    private final BasketService basketService;

    @GetMapping("")
    public String boardList(Model model){
        model.addAttribute("boards", boardService.boardList());
        return "board/boardList";
    }

    @GetMapping("/{boardId}")
    public String boardInfo(@PathVariable Long boardId, Model model){
        Board board = boardService.findBoard(boardId);
        Long countView = board.getCountView() + 1L;

        board.setCountView(countView);
        boardService.updateView(board.getId(), board);

        model.addAttribute("board", board);
        return "board/boardInfo";
    }

    @DeleteMapping("/{boardId}/delete")
    public String boardDelete(@PathVariable Long boardId, HttpServletRequest request) {

        String loginEmail = (String) request.getSession().getAttribute("loginMember");
        String boardEmail = boardService.findBoard(boardId).getMember().getEmail();
        if (sameMemberCheck(loginEmail, boardEmail)) {
            //itemService.deleteItem(boardService.findBoard(boardId).getItem().getId());
            //commentService.boardDeleteByComment(boardId);
            boardService.deleteBoard(boardId);

            return "board/boardList";
        }
        else{
                return "error/error";
            }
        }


    public boolean sameMemberCheck(String email1, String email2){
        if (email1.equals(email2)) {
            return true;
        }
        else
            return false;
    }

}
