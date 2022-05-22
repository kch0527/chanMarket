package com.example.market.controller;

import com.example.market.entity.Comment;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.CommentService;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@Data
@RequestMapping( "/chanMarket/itemList")
public class CommentController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("{itemId}")
    public String itemComment(@PathVariable Long itemId, HttpServletRequest request, @ModelAttribute Comment comment, RedirectAttributes redirectAttributes)
    {
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
    public String commentEdit(@PathVariable Long commentId, Model model){
        Comment comment = commentService.findComment(commentId);
        model.addAttribute(comment);
        return "comments/commentEdit";
    }

    @PutMapping("{commentId}/commentEdit")
    public String commentEdit(@PathVariable Long commentId, Comment comment){
        commentService.editComment(commentId, comment);
        Comment findComment = commentService.findComment(commentId);
        return "redirect:/chanMarket/itemList/" + findComment.getItem().getId();

    }

    @RequestMapping(value = "/remove" , method = RequestMethod.GET)
    public String remove(@RequestParam("commentId") Long commentId, RedirectAttributes redirectAttributes){
        commentService.deleteComment(commentId);
        redirectAttributes.addAttribute("result","remove");
        return "redirect:/chanMarket/itemList";
    }
}
