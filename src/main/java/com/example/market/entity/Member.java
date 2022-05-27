package com.example.market.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Email(message = "올바른 이메일 입력")
    private String email;

    @NotNull(message = "이름입력")
    private String name;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @NotNull
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력")
    private String pw;

    @NotNull
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바은 휴대폰 번호를 입력")
    private String tel;


    @OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade=CascadeType.ALL)
    private Basket basket;






}
