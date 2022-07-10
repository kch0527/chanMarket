package com.example.market.controller;

import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;
import com.example.market.service.basket.BasketService;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("")
    public String joinId(@Valid MemberCreate memberCreate) {
        try {
            memberService.join(memberCreate);
            return "redirect:/chanMarket/joinSucceed";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }

    @GetMapping("joinSucceed")
    public String joinSucceed() {
        return "member/joinSucceed";
    }



    @GetMapping("myInfo")
    public String myInfo(HttpServletRequest request, Model model) {
        model.addAttribute("myInfo", memberService.findByEmail((String) request.getSession().getAttribute("loginMember")));
        return "member/myInfo";
    }

    @GetMapping("myInfo/edit")
    public String memberEditForm(Model model, HttpServletRequest request) {
        model.addAttribute("myInfo", memberService.findByEmail((String) request.getSession().getAttribute("loginMember")));
        return "member/editForm";
    }

    @PutMapping("myInfo")
    public String editItem(MemberEdit memberEdit) {
        memberService.editMember(memberEdit);
        return "redirect:/chanMarket/myInfo";
    }


}
