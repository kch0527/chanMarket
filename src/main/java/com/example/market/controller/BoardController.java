package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.service.board.BoardService;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("")
    public String boardList(Model model, HttpSession session){
        model.addAttribute("boards", boardService.boardList());
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
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
    public String boardDelete(@PathVariable Long boardId, HttpSession session) {

        String loginEmail = (String) session.getAttribute("loginMember");
        String boardEmail = boardService.findBoard(boardId).getMember().getEmail();
        if (sameMemberCheck(loginEmail, boardEmail)) {
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
