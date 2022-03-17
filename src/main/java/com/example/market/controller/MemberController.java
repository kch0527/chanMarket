package com.example.market.controller;

import com.example.market.model.Item;
import com.example.market.model.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
import com.example.market.session.SessionConst;
import com.example.market.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(){
        return "member/join";
    }

    @PostMapping("/join")
    public String joinId(@Valid Member member){
       try{
           memberService.join(member);
           return "redirect:/chanMarket/joinSucceed";
       }catch (Exception e){
           return "member/join";
       }
    }

    @GetMapping("/joinSucceed")
    public String joinSucceed(){
        return "member/joinSucceed";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/login")
    public String loginId(String email, String pw, Model model, HttpServletRequest request) throws Exception{
        Member member = memberService.login(email, pw);
        if (member == null){
            return "member/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", email);

        return "redirect:/chanMarket/itemList";
    }


}
