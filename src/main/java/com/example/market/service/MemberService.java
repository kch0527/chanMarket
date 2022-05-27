package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.entity.Member;

public interface MemberService {

    void join(Member member);
    void validateDuplicateMember(Member member);
    Member login(String email, String pw);
    Member findByEmail(String email);
    void editMember(Member updateParam);

    Member findMemberById(Long id);
    Basket findBasket(Long memberId);
}
