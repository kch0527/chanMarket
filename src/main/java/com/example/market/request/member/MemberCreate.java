package com.example.market.request.member;

import com.example.market.entity.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class MemberCreate {

    @NotBlank(message = "memberEmail 없음")
    private String email;

    @NotBlank(message = "memberName 없음")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "memberPw 없음")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력")
    private String pw;

    @NotBlank(message = "memberTel 없음")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바은 휴대폰 번호를 입력")
    private String tel;

    @Builder
    public MemberCreate(String email, String name, String pw, String tel) {
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.pw = pw;
        this.tel = tel;
    }
}
