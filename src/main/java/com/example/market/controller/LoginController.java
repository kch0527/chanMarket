package com.example.market.controller;

import com.example.market.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/login")
public class LoginController {

    private final MemberService memberService;

    @GetMapping("")
    public String login() {
        return "member/login";
    }

    @PostMapping("")
    public String loginId(String email, String pw, HttpServletRequest request){

        if (memberService.login(email, pw) == null) {
            return "error/error";
        }

        request.getSession().setAttribute("loginMember", email);

        return "redirect:/chanMarket/itemList";
    }
}
