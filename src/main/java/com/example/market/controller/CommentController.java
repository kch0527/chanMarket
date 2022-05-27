package com.example.market.controller;

import com.example.market.service.CommentService;
import com.example.market.service.ItemServiceImpl;
import com.example.market.service.MemberServiceImpl;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Data
@RequestMapping("/chanMarket/item")
public class CommentController {

    private final ItemServiceImpl itemService;
    private final MemberServiceImpl memberServiceImpl;
    private final CommentService commentService;

/*
    @PostMapping("{itemId}")
    public String itemComment(@PathVariable Long itemId, HttpServletRequest request, @ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
        try {
            Item item = itemService.readItem(itemId);
            HttpSession session = request.getSession();
            String loginMember = (String) session.getAttribute("loginMember");

            Member member = memberService.findByEmail(loginMember);
            comment.setMember(member);
            comment.setItem(item);
            comment.setNowTime(commentService.nowTime());
            Comment saveComment = commentService.addComment(comment);
            redirectAttributes.addAttribute("commentId", saveComment.getId());
            redirectAttributes.addAttribute("status", true);

            return "redirect:/chanMarket/itemList/" + itemId;
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
        return "redirect:/chanMarket/itemList/" + findComment.getItem().getId();

    }

    @DeleteMapping("delete/{commentId}")
    public String commentDelete(@ModelAttribute Comment comment, @PathVariable Long commentId, RedirectAttributes redirectAttributes) {

        try {
            commentService.deleteComment(commentId);
            redirectAttributes.addFlashAttribute("msg", "삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "오류 발생");
        }
        return "item/itemList";
    }

 */
}
