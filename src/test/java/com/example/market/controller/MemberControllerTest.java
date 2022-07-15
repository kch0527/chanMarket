package com.example.market.controller;

import com.example.market.entity.member.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;
import com.example.market.service.member.MemberService;
import com.example.market.service.member.MemberServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JpaMemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl memberService;

    @BeforeEach
    void clear(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("/post 요청")
    void test() throws Exception {

        MemberCreate request = MemberCreate.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();

        String json = objectMapper.writeValueAsString(request);

        System.out.println("------------------------");
        System.out.println(json);
        System.out.println("------------------------");

        mockMvc.perform(post("/chanMarket/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 db에 값 저장")
    void test2() throws Exception {
        MemberCreate request = MemberCreate.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when 이러한 요청을 했을 때
        mockMvc.perform(post("/chanMarket/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then 이러한 결과가 나온다.
        assertEquals(1L, memberRepository.count());
        Member member = memberRepository.findAll().get(0);
        assertEquals("jhgu12@naver.com", member.getEmail());
        assertEquals("김찬회", member.getName());
    }

    @Test
    @DisplayName("멤버 수정")
    void test7() throws Exception {

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
                .pw("123456789")
                .tel("010-2222-2222")
                .build();

        mockMvc.perform(put("/chanMarket/myInfo")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEdit))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}