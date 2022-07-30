package com.example.market.controller;

import com.example.market.entity.member.Member;
import com.example.market.oauth2.SessionUser;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        if (memberService.login(email, pw) != null) {
            //Member member = memberService.findByEmail(email);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("loginMember", email);

            return "redirect:/chanMarket/board";
        }
        else return "error/error";
    }


}
