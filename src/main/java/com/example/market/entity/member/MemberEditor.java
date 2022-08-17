package com.example.market.entity.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEditor {

    private String email;
    private String name;
    private Role role;
    private String pw;
    private String tel;

    @Builder
    public MemberEditor(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
