package com.example.market.oauth2;

import com.example.market.service.board.BoardService;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final BoardService boardService;
    private final MemberService memberService;
    
    @GetMapping("")
    public String oauthLogin(Model model, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session){

        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        model.addAttribute("boards", boardService.boardList(pageable));
        return "board/boardList";
    }

}
