package com.example.market.controller;

import com.example.market.entity.Member;
import com.example.market.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("join")
    public String joinId(@Valid Member member) {
        try {
            memberService.join(member);
            return "redirect:/chanMarket/joinSucceed";
        } catch (Exception e) {
            return "member/join";
        }
    }

    @GetMapping("joinSucceed")
    public String joinSucceed() {
        return "member/joinSucceed";
    }

    @GetMapping("login")
    public String login() {
        return "member/login";
    }

    @PostMapping("login")
    public String loginId(String email, String pw, HttpServletRequest request) throws Exception {

        if (!isValidAccount(email, pw)) {
            return "member/login";
        }

        request.getSession().setAttribute("loginMember", email);

        return "redirect:/chanMarket/itemList";
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

    @PostMapping("myInfo/edit")
    public String editItem(Member member) {
        memberService.editMember(member);
        return "redirect:/chanMarket/myInfo";
    }

    boolean isValidAccount(String email, String pw) {
        return memberService.login(email, pw) != null;
    }
}
