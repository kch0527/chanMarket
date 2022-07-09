package com.example.market.entity;

import com.example.market.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Long countView;

    @OneToMany(mappedBy = "board", cascade=CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne(mappedBy = "board", cascade=CascadeType.REMOVE)
    private Item item;

    @OneToMany(mappedBy = "board", cascade=CascadeType.REMOVE)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    public void updateView(Long countView){
        this.countView = countView;
    }

}
