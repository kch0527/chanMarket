package com.example.market.service;

import com.example.market.model.Grade;
import com.example.market.model.Item;
import com.example.market.model.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final JpaMemberRepository jpaMemberRepository;

    @Transactional
    public void join(Member member){
        validateDuplicateMember(member);
        member.setGrade(Grade.USER);
            jpaMemberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
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

}
