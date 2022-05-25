package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoom {

    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Board owner;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
