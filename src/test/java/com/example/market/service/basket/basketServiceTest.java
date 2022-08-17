package com.example.market.service.basket;

/*
import com.example.market.MarketApplication;
import com.example.market.entity.basket.BasketItem;
import com.example.market.entity.item.Item;
import com.example.market.entity.member.Member;
import com.example.market.repository.JpaBasketItemRepository;
import com.example.market.repository.JpaBasketRepository;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.member.MemberCreate;
import com.example.market.service.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = MarketApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class basketServiceTest {

    @Autowired
    private BasketServiceImpl basketService;
    @Autowired
    private JpaBasketRepository basketRepository;
    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private JpaMemberRepository memberRepository;
    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaBasketItemRepository basketItemRepository;
    @BeforeEach
    void clear(){
        basketRepository.deleteAll();
        basketItemRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입시 장바구니 생성")
    void test1(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        assertEquals(1L, basketRepository.count());
    }

    @Test
    @DisplayName("장바구니에 아이템 추가")
    void test2(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        Item item = Item.builder()
                .itemName("asd")
                .itemInformation("asd")
                .price("1000")
                .build();
        itemRepository.save(item);

        Member member = memberService.findByEmail(memberCreate.getEmail());
        member.setBasket(basketRepository.findAll().get(0));

        basketService.addBasketItem(member, item);

        assertEquals(1L, basketRepository.count());
        assertEquals(1L, basketItemRepository.count());

        BasketItem basketItem = basketItemRepository.findAll().get(0);
        assertEquals("asd", basketItem.getItem().getItemName());
        assertEquals("1000", basketItem.getItem().getPrice());
        assertEquals("asd", basketItem.getItem().getItemInformation());

    }

    @Test
    @DisplayName("장바구니 아이템 삭제")
    void test3(){
        MemberCreate memberCreate = MemberCreate.builder()
                .email("jhgu@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberService.join(memberCreate);

        Item item = Item.builder()
                .itemName("asd")
                .itemInformation("asd")
                .price("1000")
                .build();
        itemRepository.save(item);

        Member member = memberService.findByEmail(memberCreate.getEmail());
        member.setBasket(basketRepository.findAll().get(0));

        basketService.addBasketItem(member, item);

        assertEquals(1L, basketItemRepository.count());

        BasketItem basketItem = basketItemRepository.findAll().get(0);
        basketService.deleteBasketItem(basketItem.getId());
        assertEquals(0L, basketItemRepository.count());
    }
}
*/
