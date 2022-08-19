package com.example.market.entity.board;

import com.example.market.entity.ChatRoom;
import com.example.market.entity.comment.Comment;
import com.example.market.entity.member.Member;
import com.example.market.entity.member.MemberEditor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long countView;
    private String title;
    private String price;
    private String itemInformation;
    @Column(length = 20000)
    private String category;

    private String filename;
    private String filepath;


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    public void updateView(Long countView) {
        this.countView = countView;
    }

    @Builder
    public Board(Member member, Long countView, String title, String price, String itemInformation, String category, String filename, String filepath) {
        this.member = member;
        this.countView = countView;
        this.title = title;
        this.price = price;
        this.itemInformation = itemInformation;
        this.category = category;
        this.filename = filename;
        this.filepath = filepath;
    }

    public BoardEditor.BoardEditorBuilder toEditor(){
        return BoardEditor.builder()
                .title(title)
                .price(price)
                .itemInformation(itemInformation)
                .category(category);
    }

    public void edit(BoardEditor boardEditor){
        title = boardEditor.getTitle();
        price = boardEditor.getPrice();
        itemInformation = boardEditor.getItemInformation();
        category = boardEditor.getCategory();

    }
}
