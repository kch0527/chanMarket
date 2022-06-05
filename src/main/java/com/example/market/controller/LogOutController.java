package com.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class LogOutController {

    @PostMapping("")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginMember");
        //request.getSession().invalidate();
        return "redirect:/chanMarket/login";
    }
}
