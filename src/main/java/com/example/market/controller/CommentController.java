package com.example.market.controller;

import com.example.market.entity.Comment;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.CommentService;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Data
@RequestMapping( "/chanMarket/itemList")
public class CommentController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("{itemId}")
    public String itemComment(@PathVariable Long itemId, HttpServletRequest request, @ModelAttribute Comment comment){
        Item item = itemService.readItem(itemId);
        HttpSession session = request.getSession();
        String loginMember = (String) session.getAttribute("loginMember");

        Member member = memberService.findByEmail(loginMember);
        comment.setMember(member);
        comment.setItem(item);
        comment.setNowTime(commentService.nowTime());
        commentService.addComment(comment);


        return "redirect:/chanMarket/itemList/" + item.getId();
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
        return "redirect:/chanMarket/itemList/" + comment.getItem().getId();

    }
}
