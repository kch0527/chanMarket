package com.example.market.controller;

import com.example.market.entity.ChatRoom;
import com.example.market.service.board.BoardService;
import com.example.market.service.chatRoom.ChatRoomService;
import com.example.market.service.member.MemberService;
import com.example.market.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final MessageService messageService;

    @PostMapping("/{boardId}/ChatCreate")
    public void createChatRoom(@PathVariable Long boardId, ChatRoom chatRoom, HttpSession session) {
        chatRoomService.createChatRoom(
                chatRoom,
                boardService.findBoard(boardId),
                memberService.findByEmail((String) session.getAttribute("loginMember")));
    }

    @GetMapping("/{memberId}/ChatList")
    public String chatRoomList(@PathVariable Long memberId, Model model, HttpSession session) {
        model.addAttribute("myChatRooms", chatRoomService.findMyRoom(memberId));
        model.addAttribute("myInfo", memberService.findByEmail((String) session.getAttribute("loginMember")));
        return "chat/chatRoomList";
    }

    @DeleteMapping("{roomId}/ChatList/delete")
    public String delChatRoom(@PathVariable Long roomId) {
        chatRoomService.delChatRoom(roomId);
        return "chat/chatRoomList";
    }

    @GetMapping("{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model, HttpSession session) {
        model.addAttribute("chatRoom", chatRoomService.findRoom(roomId));
        model.addAttribute("userid", memberService.findByEmail((String) session.getAttribute("loginMember")));
        model.addAttribute("messageList", messageService.messageList(roomId));

        return "chat/chatRoom";
    }
}
