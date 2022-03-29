package com.example.market.controller;

import com.example.market.model.Item;
import com.example.market.model.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.service.ItemService;
import com.example.market.service.MemberService;
import com.example.market.session.SessionConst;
import com.example.market.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    public String loginId(String email, String pw, HttpServletRequest request) throws Exception{
        Member member = memberService.login(email, pw);
        if (member == null){
            return "member/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", email);

        return "redirect:/chanMarket/itemList";
    }

    @GetMapping("/myInfo")
    public String myInfo(Member member, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String sessionMember = (String)session.getAttribute("loginMember");
        Member findByMember = memberService.findByEmail(sessionMember);
        member.setName(findByMember.getName());
        member.setEmail(findByMember.getEmail());
        member.setTel(findByMember.getTel());
        member.setGrade(findByMember.getGrade());

        List<Item> items = findByMember.getItems();

        model.addAttribute("myInfo",member);
        model.addAttribute("myItems",items);
        return "member/myInfo";
    }

    @GetMapping("/myInfo/edit")
    public String memberEditForm(Member member, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionMember = (String)session.getAttribute("loginMember");
        Member findByMember = memberService.findByEmail(sessionMember);
        member.setName(findByMember.getName());
        member.setEmail(findByMember.getEmail());
        member.setTel(findByMember.getTel());
        member.setGrade(findByMember.getGrade());

        return "member/editForm";
    }
    @PostMapping("/myInfo/edit")
    public String editItem(Member member){
        memberService.editMember(member);
        return "redirect:/chanMarket/myInfo";
    }

}
