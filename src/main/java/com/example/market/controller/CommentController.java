package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.entity.Comment;
import com.example.market.entity.Member;
import com.example.market.service.*;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Data
@RequestMapping("/chanMarket/board")
public class CommentController {

    private final ItemServiceImpl itemService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("{boardId}")
    public String itemComment(@PathVariable Long boardId, HttpServletRequest request, @ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
        try {
            Board board = boardService.findBoard(boardId);
            Member loginMember = memberService.findByEmail((String) request.getSession().getAttribute("loginMember"));

            comment.setBoard(board);
            comment.setMember(loginMember);
            comment.setNowTime(commentService.nowTime());
            Comment saveComment = commentService.addComment(comment);
            redirectAttributes.addAttribute("commentId", saveComment.getId());
            redirectAttributes.addAttribute("status", true);

            return "redirect:/chanMarket/board/" + boardId;
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }

    @GetMapping("{commentId}/commentEdit")
    public String commentEdit(@PathVariable Long commentId, Model model) {
        Comment comment = commentService.findComment(commentId);
        model.addAttribute(comment);
        return "comments/commentEdit";
    }

    @PutMapping("{commentId}/commentEdit")
    public String commentEdit(@PathVariable Long commentId, Comment comment) {
        commentService.editComment(commentId, comment);
        Comment findComment = commentService.findComment(commentId);
        return "redirect:/chanMarket/board/" + findComment.getBoard().getId();

    }
/*
    @DeleteMapping("{commentId}/deleteComment")
    public String commentDelete(@ModelAttribute Comment comment, @PathVariable Long commentId, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        try {
            if (sameMemberCheck(comment.getMember().getEmail(), (String) request.getSession().getAttribute("loginMember"))){
                commentService.deleteComment(commentId);
                redirectAttributes.addFlashAttribute("msg", "삭제 완료");
            }
            else {
                redirectAttributes.addFlashAttribute("msg", "권한 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "오류 발생");
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
*/

}
