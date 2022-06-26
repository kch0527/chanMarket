package com.example.market.controller;

import com.example.market.entity.ChatRoom;
import com.example.market.entity.Member;
import com.example.market.service.BoardService;
import com.example.market.service.ChatRoomService;
import com.example.market.service.MemberService;
import com.example.market.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chanMarket/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final MessageService messageService;

    @GetMapping("/{boardId}/ChatCreate")
    public String createChatRoom(@PathVariable Long boardId, Model model, HttpServletRequest request){
        if (boardService.findBoard(boardId).getMember() != memberService.findByEmail((String) request.getSession().getAttribute("loginMember"))) {
            if (chatRoomService.chatRoomDeduplication(boardId, memberService.findByEmail((String) request.getSession().getAttribute("loginMember")).getId())) {
                model.addAttribute("board", boardService.findBoard(boardId));
                return "chat/createForm";
            }
            else return "chat/chatExistError";
        }
        else return "chat/chatError";
    }

    @PostMapping("/{boardId}/ChatCreate")
    public String createChatRoom(@PathVariable Long boardId, ChatRoom chatRoom, HttpServletRequest request){
            chatRoomService.createChatRoom(
                    chatRoom,
                    boardService.findBoard(boardId),
                    memberService.findByEmail((String) request.getSession().getAttribute("loginMember")));

            return "redirect:/chanMarket/chat/" + chatRoom.getId();
    }

    @GetMapping("/{memberId}/ChatList")
    public String chatRoomList(@PathVariable Long memberId, Model model){
        model.addAttribute("myChatRooms", chatRoomService.findMyRoom(memberId));
        return "chat/chatRoomList";
    }

    @DeleteMapping("{roomId}/ChatList/delete")
    public String delChatRoom(@PathVariable Long roomId){
        chatRoomService.delChatRoom(roomId);
        return "chat/chatRoomList";
    }

    @GetMapping("{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model, HttpServletRequest request){
        model.addAttribute("chatRoom",chatRoomService.findRoom(roomId));
        model.addAttribute("userid", memberService.findByEmail((String)request.getSession().getAttribute("loginMember")));
        model.addAttribute("messageList", messageService.messageList(roomId));

        return "chat/chatRoom";
    }


}
