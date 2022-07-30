package com.example.market.oauth2;

import com.example.market.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final BoardService boardService;
    private final HttpSession httpSession;
    
    @GetMapping("")
    public String oauthLogin(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("googleLogin");

        if (user != null){
            model.addAttribute("googleLogin", user);
        }

        model.addAttribute("boards", boardService.boardList());
        return "board/boardList";
    }

    @GetMapping("chanMarket/myInfo/google")
    public String googleInfo(){
        return "";
    }

}
