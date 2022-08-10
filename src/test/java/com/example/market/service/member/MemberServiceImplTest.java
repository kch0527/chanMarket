package com.example.market.service.member;

import com.example.market.MarketApplication;
import com.example.market.entity.member.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = MarketApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberServiceImplTest {

    @Autowired
    private JpaMemberRepository jpaMemberRepository;

    @Autowired
    private MemberServiceImpl memberService;

    @BeforeEach
    void clear(){
        jpaMemberRepository.deleteAll();
    }


    @Test
    @DisplayName("회원 가입")
    void test1(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        assertEquals(1L, jpaMemberRepository.count());
        Member member = jpaMemberRepository.findAll().get(0);
        assertEquals("jhgu12@naver.com", member.getEmail());
        assertEquals("김찬회", member.getName());
        assertEquals("123412341", member.getPw());
        assertEquals("010-1111-1111", member.getTel());
    }

    @Test
    @DisplayName("이메일로 회원찾기")
    void test2(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        Member member = jpaMemberRepository.findByEmail("jhgu12@naver.com");

        Assertions.assertNotNull(member);
        assertEquals("jhgu12@naver.com", member.getEmail());
        assertEquals("김찬회", member.getName());
        assertEquals("123412341", member.getPw());
        assertEquals("010-1111-1111", member.getTel());
    }

    @Test
    @DisplayName("멤버정보 수정")
    void test3(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        MemberEdit memberEdit = MemberEdit.builder()
                .email("jhgu12@naver.com")
                .name("찬회")
                .pw("123456781")
                .tel("010-2222-2222")
                .build();

        memberService.editMember(memberEdit);

        Member member = jpaMemberRepository.findByEmail(memberCreate.getEmail());
        Member updateMember =jpaMemberRepository.findById(member.getId()).orElseThrow(()-> new RuntimeException("없는 멤버 id: " + member.getId()));
        assertEquals("jhgu12@naver.com", updateMember.getEmail());
        assertEquals("찬회", updateMember.getName());
        assertEquals("123456781", updateMember.getPw());
        assertEquals("010-2222-2222", updateMember.getTel());
    }

}