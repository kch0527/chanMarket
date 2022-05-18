package com.example.market.controller;

import com.example.market.model.Comment;
import com.example.market.model.Image;
import com.example.market.model.Item;
import com.example.market.model.Member;
import com.example.market.service.BasketServiceImpl;
import com.example.market.service.CommentService;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
import com.example.market.session.SessionConst;
import lombok.Data;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Data
@RequestMapping("/chanMarket/itemList")
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final BasketServiceImpl basketService;


    @GetMapping
    public String itemList(Model model){
        List<Item> items = itemService.itemList();
        model.addAttribute(items);
        return "item/itemList";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemService.readItem(itemId);
        List<Comment> comments = item.getComments();
        model.addAttribute("item", item);
        model.addAttribute("comment", comments);
        return "item/itemInfo";
    }

    @GetMapping("/{itemId}/comment")
    public String itemComment(){
        return "item/commentForm";
    }

    @PostMapping("/{itemId}/comment")
    public String comment(@PathVariable Long itemId, HttpServletRequest request, @ModelAttribute Comment comment, RedirectAttributes redirectAttributes){
        Item item = itemService.readItem(itemId);
        HttpSession session = request.getSession();
        String loginMember = (String) session.getAttribute("loginMember");

        Member member = memberService.findByEmail(loginMember);
        comment.setMember(member);
        comment.setItem(item);
        comment.setNowTime(commentService.nowTime());
        Comment saveComment = commentService.addComment(comment);

        redirectAttributes.addAttribute("comments",saveComment.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/chanMarket/itemList/" + item.getId();
    }

    @GetMapping("/{itemId}/delete")
    public String itemDelete(@PathVariable Long itemId, Model model){
        Item findItem = itemService.readItem(itemId);
        model.addAttribute("item",findItem);
        return "item/deleteForm";
    }

    @PostMapping("/{itemId}/delete")
    public String itemDelete(@PathVariable Long itemId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String member = (String) session.getAttribute("loginMember");
        Item item = itemService.readItem(itemId);

        if (item.getMember().getEmail().equals(member)) {
            itemService.deleteItem(itemId);
            return "redirect:/chanMarket/itemList";
        }
        return "member/no";
    }
/*
    @DeleteMapping("/{itemId}")
    public String itemDelete2(@PathVariable Long itemId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String member = (String) session.getAttribute("loginMember");
        Item item = itemService.readItem(itemId);

        if (item.getMember().getEmail().equals(member)) {
            itemService.deleteItem(itemId);
            return "redirect:/chanMarket/itemList";
        }
        return "member/no";
    }
*/
    @GetMapping("/{itemId}/itemBasket")
    public String itemBasket(@PathVariable Long itemId, Model model){
        Item item = itemService.readItem(itemId);
        model.addAttribute("item", item);
        return "item/itemBasket";
    }

    @PostMapping("/{itemId}/itemBasket")
    public String itemPutBasket(@PathVariable("id") Long itemId, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String loginMember = (String) session.getAttribute("loginMember");
        Member member = memberService.findByEmail(loginMember);
        Item item = itemService.readItem(itemId);

        basketService.addBasket(member, item);
        model.addAttribute("basketItem", item);
        return "member/myInfo";
    }

    @GetMapping("/add")
    public String addForm(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String member = (String)session.getAttribute("loginMember");
        request.setAttribute("email", member);

        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String member = (String)session.getAttribute("loginMember");
            Member findByMember = memberService.findByEmail(member);
            item.setMember(findByMember);
            Item saveItem = itemService.addItem(item);
            redirectAttributes.addAttribute("itemId", saveItem.getId());
            redirectAttributes.addAttribute("status", true);
            return "redirect:/chanMarket/itemList/" + item.getId();
        }catch (Exception e){
            return "item/addForm";
        }
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionMember = (String)session.getAttribute("loginMember");
        Item findItem = itemService.readItem(itemId);

        if (findItem.getMember().getEmail().equals(sessionMember)) {
            Item item = itemService.readItem(itemId);
            model.addAttribute("item", item);
            return "item/editForm";
        }
        return "member/no";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Item item){
        itemService.editItem(itemId, item);
        return "redirect:/chanMarket/itemList/{itemId}";
    }

}
