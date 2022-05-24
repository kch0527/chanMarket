package com.example.market.controller;

import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.service.BasketServiceImpl;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
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
@RequestMapping("/chanMarket/itemList")
public class BasketController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final BasketServiceImpl basketService;

    @GetMapping("itemBasket")
    public String basketList() {
        return "basket/myBasket";
    }

    @GetMapping("{itemId}/itemBasket")
    public String itemBasket(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.readItem(itemId));
        return "item/itemBasket";
    }

    @PostMapping("{itemId}/itemBasket")
    public String itemPutBasket(@PathVariable Long itemId, HttpServletRequest request, Model model) {
        String loginMember = (String) request.getSession().getAttribute("loginMember");
        Member member = memberService.findByEmail(loginMember);
        Item item = itemService.readItem(itemId);

        boolean isAddOk = basketService.addBasketItem(member, item);

        if(isAddOk) {
            model.addAttribute("basketItem", item);
            return "basket/myBasket";
        }
        else {
            return "error/error";
        }
    }
}
