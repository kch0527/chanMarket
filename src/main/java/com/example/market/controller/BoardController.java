package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.service.BoardService;
import com.example.market.service.ItemService;
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


    @GetMapping("")
    public String boardList(Model model){
        model.addAttribute("boards", boardService.boardList());
        return "board/boardList";
    }

    @GetMapping("/{boardId}")
    public String boardInfo(@PathVariable Long boardId, Model model){
        model.addAttribute("board", boardService.findBoard(boardId));
        return "board/boardInfo";
    }

    @DeleteMapping("/{boardId}/delete")
    public String boardDelete(@PathVariable Long boardId, HttpServletRequest request){
        try {
            String loginEmail = (String) request.getSession().getAttribute("loginMember");
            String boardEmail = boardService.findBoard(boardId).getMember().getEmail();
            if (sameMemberCheck(loginEmail, boardEmail)) {
                itemService.deleteItem(boardService.findBoard(boardId).getItem().getId());
                boardService.deleteBoard(boardId);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "board/boardList";
    }

    public boolean sameMemberCheck(String email1, String email2){
        if (email1.equals(email2)) {
            return true;
        }
        else
            return false;
    }

}
