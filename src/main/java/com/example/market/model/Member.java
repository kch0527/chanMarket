package com.example.market.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotNull(message = "이름입력")
    private String name;

    @NotNull
    @Email(message = "올바른 이메일 입력")
    private String email;

    @NotNull
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력")
    private String pw;

    @NotNull
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바은 휴대폰 번호를 입력")
    private String tel;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    @OneToMany
    private List<Item> basket = new ArrayList<>();


}
