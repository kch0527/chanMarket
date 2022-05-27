package com.example.market.controller;

import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.*;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Data
@RequestMapping("/chanMarket/basket")
public class BasketController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final BasketService basketService;

    @GetMapping("{basketId}")
    public String basketList(@PathVariable Long basketId, Model model) {
        model.addAttribute("list",basketService.BasketList());
        return "basket/myBasket";
    }

    @GetMapping("{itemId}/add")
    public String itemPutBasket(@PathVariable Long itemId, Model model, HttpServletRequest request) {
        model.addAttribute("member", memberService.findByEmail((String) request.getSession().getAttribute("loginMember")));
        model.addAttribute("item", itemService.readItem(itemId));
        return "basket/addForm";
    }

    @PostMapping("{itemId}/add")
    public String itemPutBasket(@PathVariable Long itemId, HttpServletRequest request, Model model) {
        Member member = memberService.findByEmail((String) request.getSession().getAttribute("loginMember"));
        Item item = itemService.readItem(itemId);

        boolean isAddOk = basketService.addBasketItem(member, item);

        if(isAddOk) {
            return "redirect:/chanMarket/basket/" + member.getBasket().getId();
        }
        else {
            return "basket/exist";
        }
    }

}
