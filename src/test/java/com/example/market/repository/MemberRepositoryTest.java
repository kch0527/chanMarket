package com.example.market.repository;

import com.example.market.entity.Grade;
import com.example.market.entity.Member;
import com.example.market.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) //스프링과 관련된 테스트를 하기위한
@SpringBootTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    @Transactional  //테스트코드 테스트가 끝나면 바로 롤백해버려서 db값 저장안됨
    @Rollback(value = false)
    public void testMember() throws Exception{
        Member member = new Member();
        member.setName("chan");
        member.setEmail("asdf@naver.com");
        member.setPw("123456789");
        member.setTel("01022222222");
        member.setGrade(Grade.USER);
        memberServiceImpl.join(member);
    }

}