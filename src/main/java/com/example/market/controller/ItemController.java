package com.example.market.controller;

import com.example.market.entity.Comment;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.BasketServiceImpl;
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
import java.util.List;

@Controller
@Data
@RequestMapping( "/chanMarket/itemList")
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

    @GetMapping("add")
    public String addForm(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String member = (String)session.getAttribute("loginMember");
        request.setAttribute("email", member);

        return "item/addForm";
    }

    @PostMapping("")
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

}
