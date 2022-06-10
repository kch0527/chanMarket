package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.entity.Grade;
import com.example.market.entity.Member;
import com.example.market.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final JpaMemberRepository jpaMemberRepository;

    @Transactional
    public void join(Member member){
        validateDuplicateMember(member);
        member.setGrade(Grade.USER);
        jpaMemberRepository.save(member);
    }

    public void validateDuplicateMember(Member member){
        Member findMember = jpaMemberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    public Member login(String email, String pw){
        Member member = jpaMemberRepository.findMember(email, pw);
        return member;
    }

    public Member findByEmail(String email){
        Member member = jpaMemberRepository.findByEmail(email);
        return member;
    }

    @Transactional
    public void editMember(Member updateParam){
        Member findMember = jpaMemberRepository.findByEmail(updateParam.getEmail());
        findMember.setName(updateParam.getName());
        findMember.setEmail(updateParam.getEmail());
        findMember.setTel(updateParam.getTel());
        findMember.setGrade(updateParam.getGrade());
        jpaMemberRepository.save(findMember);
    }

    public Member findMemberById(Long id){
        return jpaMemberRepository.getById(id);
    }

    public Basket findBasket(Long memberId){
        return findMemberById(memberId).getBasket();
    }

}
