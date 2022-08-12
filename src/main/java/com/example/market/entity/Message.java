package com.example.market.entity;

import com.example.market.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @Size(max = 30)
    @NotNull
    private String message;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime nowTime;


    private String caller;

    private String receiver;

    @Builder
    public Message(ChatRoom chatRoom, String message, LocalDateTime nowTime, String caller, String receiver) {
        this.chatRoom = chatRoom;
        this.message = message;
        this.nowTime = nowTime;
        this.caller = caller;
        this.receiver = receiver;
    }
}
