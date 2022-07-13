package com.example.market.controller;

import com.example.market.entity.item.Item;
import com.example.market.entity.member.Member;
import com.example.market.service.basket.BasketService;
import com.example.market.service.item.ItemService;
import com.example.market.service.member.MemberService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("list",basketService.BasketList(basketId));
        return "basket/myBasket";
    }

    @GetMapping("{itemId}/add")
    public String itemPutBasket(@PathVariable Long itemId, Model model, HttpServletRequest request) {
        if (itemService.readItem(itemId).getBoard().getMember() != memberService.findByEmail((String) request.getSession().getAttribute("loginMember"))) {
            model.addAttribute("member", memberService.findByEmail((String) request.getSession().getAttribute("loginMember")));
            model.addAttribute("item", itemService.readItem(itemId));
            return "basket/addForm";
        }
        else return "basket/error";
    }

    @PostMapping("{itemId}/add")
    public String itemPutBasket(@PathVariable Long itemId, HttpServletRequest request) {
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

    @DeleteMapping("{basketId}/{baskItemId}/delete")
    public String deleteBasketItem(@PathVariable Long basketId, @PathVariable Long baskItemId){
        basketService.deleteBasketItem(baskItemId);
        return "redirect:/chanMarket/basket/" + basketId;
    }

}
