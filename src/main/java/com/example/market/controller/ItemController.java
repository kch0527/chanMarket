package com.example.market.controller;

import com.example.market.entity.Board;
import com.example.market.entity.item.Item;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.item.ItemEdit;
import com.example.market.service.basket.BasketServiceImpl;
import com.example.market.service.board.BoardService;
import com.example.market.service.comment.CommentService;
import com.example.market.service.item.ItemService;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/chanMarket/item")
public class ItemController {

    private final ItemService itemService;
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("add")
    public String addForm(HttpSession session, Model model){
        model.addAttribute("email", (String) session.getAttribute("loginMember"));
        return "item/addForm";
    }

    @PostMapping("")
    public String addItem(ItemCreate itemCreate, Board board, HttpSession session){
        try {
            itemCreate.setBoard(boardService.createBoard(board, memberService.findByEmail((String) session.getAttribute("loginMember"))));
            itemService.addItem(itemCreate);
            return "redirect:/chanMarket/board/" + board.getId();
        }catch (Exception e){
            return "error/error";
        }
    }

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, HttpSession session){
        String loginMember = (String) session.getAttribute("loginMember");
        String ownerMember = itemService.readItem(itemId).getBoard().getMember().getEmail();

        if (editAuthority(loginMember, ownerMember)) {
            model.addAttribute("item", itemService.readItem(itemId));
            return "item/editForm";
        }
        return "error/error";
    }

    @PutMapping("{itemId}")
    public String editItem(@PathVariable Long itemId, ItemEdit itemEdit){
        itemService.editItem(itemId, itemEdit);
        return "redirect:/chanMarket/board/" + itemService.readItem(itemId).getBoard().getId();
    }

    public boolean editAuthority(String email1, String email2){
        if (email1.equals(email2)){
            return true;
        }
        else
            return false;
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




 */

}
