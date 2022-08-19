package com.example.market.controller;

import com.example.market.entity.board.Board;
import com.example.market.entity.member.Member;
import com.example.market.service.basket.BasketService;
import com.example.market.service.board.BoardService;
import com.example.market.service.member.MemberService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Data
@RequestMapping("/chanMarket/basket")
public class BasketController {

    private final MemberService memberService;
    private final BasketService basketService;
    private final BoardService boardService;

    @GetMapping("{basketId}")
    public String basketList(@PathVariable Long basketId, Model model, HttpSession session) {
        model.addAttribute("list",basketService.BasketList(basketId));
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "basket/myBasket";
    }

    @GetMapping("{boardId}/add")
    public String boardPutBasket(@PathVariable Long boardId, Model model, HttpSession session) {
        if (boardService.findBoard(boardId).getMember() != memberService.findByEmail((String) session.getAttribute("loginMember"))) {
            model.addAttribute("member", memberService.findByEmail((String) session.getAttribute("loginMember")));
            model.addAttribute("board", boardService.findBoard(boardId));
            return "basket/addForm";
        }
        else return "basket/error";
    }

    @PostMapping("{boardId}/add")
    public String boardPutBasket(@PathVariable Long boardId, HttpSession session) {
        Member member = memberService.findByEmail((String) session.getAttribute("loginMember"));
        Board board = boardService.findBoard(boardId);

        boolean isAddOk = basketService.addBasketItem(member, board);

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
