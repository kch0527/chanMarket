package com.example.market.service.member;

import com.example.market.entity.member.Member;
import com.example.market.entity.member.MemberEditor;
import com.example.market.exception.MemberNotFound;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;
import com.example.market.service.basket.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final JpaMemberRepository jpaMemberRepository;
    private final BasketService basketService;


    public void join(MemberCreate memberCreate){
        Member member = Member.builder()
                .email(memberCreate.getEmail())
                .name(memberCreate.getName())
                .pw(memberCreate.getPw())
                .tel(memberCreate.getTel())
                .build();
        validateDuplicateMember(member);
        basketService.addBasket(member);
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


    public void editMember(MemberEdit updateParam){
        Member findMember = jpaMemberRepository.findByEmail(updateParam.getEmail());
        MemberEditor.MemberEditorBuilder memberEditorBuilder = findMember.toEditor();
        MemberEditor memberEditor = memberEditorBuilder
                .email(updateParam.getEmail())
                .name(updateParam.getName())
                .pw(updateParam.getPw())
                .tel(updateParam.getTel())
                        .build();
        findMember.edit(memberEditor);
    }

    public Member findMemberById(Long id){
        return jpaMemberRepository.getById(id);
    }

    @Override
    public void deleteMember(Member member) {
        jpaMemberRepository.delete(jpaMemberRepository.findById(member.getId()).orElseThrow(MemberNotFound::new));
    }


}
