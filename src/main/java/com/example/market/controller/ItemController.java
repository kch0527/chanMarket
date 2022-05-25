package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/chanMarket/itemList")
public class ItemController {

    private final ItemService itemService;
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final BasketServiceImpl basketService;


    @GetMapping("add")
    public String addForm(HttpServletRequest request, Model model){
        model.addAttribute("email", (String) request.getSession().getAttribute("loginMember"));
        return "item/addForm";
    }

    @PostMapping("")
    public String addItem(Item item, Board board, HttpServletRequest request){
        try {
            item.setBoard(boardService.createBoard(board, memberService.findByEmail((String) request.getSession().getAttribute("loginMember"))));
            itemService.addItem(item);
            return "redirect:/chanMarket/board/" + board.getId();
        }catch (Exception e){
            return "error/error";
        }
    }
/*
    @DeleteMapping("{itemId}")
    public String itemDelete(@PathVariable Long itemId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String member = (String) session.getAttribute("loginMember");
        Item item = itemService.readItem(itemId);

        if (item.getMember().getEmail().equals(member)) {
            commentService.itemDeleteByComment(item);
            itemService.deleteItem(itemId);
            return "redirect:/chanMarket/itemList";
        }
        return "member/no";
    }


    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemService.readItem(itemId);
        List<Comment> comments = item.getComments();
        model.addAttribute("item", item);
        model.addAttribute("comment", comments);
        return "item/itemInfo";
    }



    @GetMapping("{itemId}/delete")
    public String itemDelete(@PathVariable Long itemId, Model model){
        Item findItem = itemService.readItem(itemId);
        model.addAttribute("item",findItem);
        return "item/deleteForm";
    }


    @GetMapping("{itemId}/edit")
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

    @PutMapping("{itemId}")
    public String editItem(@PathVariable Long itemId, Item item){
        itemService.editItem(itemId, item);
        return "redirect:/chanMarket/itemList/" + itemId;
    }

 */

}
