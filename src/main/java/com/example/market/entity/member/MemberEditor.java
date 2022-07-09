package com.example.market.entity.member;

import com.example.market.entity.Grade;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEditor {

    private String email;
    private String name;
    private Grade grade;
    private String pw;
    private String tel;

    @Builder
    public MemberEditor(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.grade = Grade.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
