package com.example.market.service.member;

import com.example.market.entity.member.Member;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;

public interface MemberService {

    void join(MemberCreate memberCreate);
    void validateDuplicateMember(Member member);
    Member login(String email, String pw);
    Member findByEmail(String email);
    void editMember(MemberEdit updateParam);

    Member findMemberById(Long id);
}
