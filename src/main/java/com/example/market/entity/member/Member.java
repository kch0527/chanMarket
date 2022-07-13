package com.example.market.entity.member;

import com.example.market.entity.*;
import com.example.market.entity.basket.Basket;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String name;
    private Grade grade;
    private String pw;
    private String tel;

    @Builder
    public Member(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.grade = Grade.USER;
        this.pw = pw;
        this.tel = tel;
    }

    public MemberEditor.MemberEditorBuilder toEditor(){
        return MemberEditor.builder()
                .email(email)
                .name(name)
                .pw(pw)
                .tel(tel);
    }

    public void edit(MemberEditor memberEditor){
        email = memberEditor.getEmail();
        name = memberEditor.getName();
        pw = memberEditor.getPw();
        tel = memberEditor.getTel();

    }

    @OneToMany(mappedBy = "member", cascade=CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade=CascadeType.REMOVE)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade=CascadeType.REMOVE)
    private Basket basket;






}
