package com.example.market.controller;

import com.example.market.entity.board.Board;
import com.example.market.entity.comment.Comment;
import com.example.market.request.board.BoardCreate;
import com.example.market.request.board.BoardEdit;
import com.example.market.service.board.BoardService;
import com.example.market.service.comment.CommentService;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("")
    public String boardList(Model model, HttpSession session, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards", boardService.boardList(pageable));
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/boardList";
    }

    @GetMapping("/{boardId}")
    public String boardInfo(@PathVariable Long boardId, Model model, HttpSession session, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Comment> comments = commentService.commentList(boardId, pageable);

        Board board = boardService.findBoard(boardId);
        Long countView = board.getCountView() + 1L;
        board.setCountView(countView);
        boardService.updateView(board.getId(), board);

        model.addAttribute("commentPage", comments);
        model.addAttribute("board", board);
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/boardInfo";
    }

    @GetMapping("/add")
    public String addForm(HttpSession session, Model model){
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "board/addForm";
    }

    @PostMapping("/add")
    public String createBoard(@Valid BoardCreate boardCreate, HttpSession session){
        try {
            Board createBoard = boardService.createBoard(boardCreate, memberService.findByEmail((String) session.getAttribute("loginMember")));
            return "redirect:/chanMarket/board/" + createBoard.getId();
        }catch (Exception e){
            return "error/error";
        }
    }

    @GetMapping("/{boardId}/edit")
    public String boardEditForm(@PathVariable Long boardId, Model model, HttpSession session) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        model.addAttribute("board", boardService.findBoard(boardId));
        return "board/editForm";
    }

    @PutMapping("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, BoardEdit boardEdit) {
        boardService.editBoard(boardId, boardEdit);
        return "redirect:/chanMarket/board/" + boardId;
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
