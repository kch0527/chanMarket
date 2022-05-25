package com.example.market.controller;

import com.example.market.service.BasketServiceImpl;
import com.example.market.service.ItemServiceImpl;
import com.example.market.service.MemberServiceImpl;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Data
@RequestMapping("/chanMarket/itemList")
public class BasketController {

    private final ItemServiceImpl itemService;
    private final MemberServiceImpl memberServiceImpl;
    private final BasketServiceImpl basketService;
/*
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
 */
}
