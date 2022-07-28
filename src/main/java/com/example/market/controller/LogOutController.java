package com.example.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/board")
public class LogOutController {

    @PostMapping("")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/chanMarket/login";
    }
}
