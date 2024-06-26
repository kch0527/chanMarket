package com.example.market.controller;


import com.example.market.entity.member.Member;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;

import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/join")
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
    public String myInfo(HttpSession session, Model model) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "member/myInfo";
    }

    @GetMapping("myInfo/edit")
    public String memberEditForm(Model model, HttpSession session) {
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "member/editForm";
    }

    @PutMapping("myInfo")
    public String editItem(MemberEdit memberEdit) {
        memberService.editMember(memberEdit);
        return "redirect:/chanMarket/myInfo";
    }

    @DeleteMapping("myInfo")
    public void deleteMember(HttpSession session){
        Member member = memberService.findByEmail((String) session.getAttribute("loginMember"));
        memberService.deleteMember(member);
        session.invalidate();
    }



}
