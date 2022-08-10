package com.example.market.service.item;

import com.example.market.MarketApplication;
import com.example.market.entity.Board;
import com.example.market.entity.item.Item;
import com.example.market.entity.member.Member;
import com.example.market.repository.JpaItemRepository;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.item.ItemEdit;
import com.example.market.request.member.MemberCreate;
import com.example.market.request.member.MemberEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = MarketApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemServiceImplTest {

    @Autowired
    private JpaItemRepository repository;

    @Autowired
    private ItemServiceImpl itemService;

    @BeforeEach
    void clear(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("상품 등록")
    void test1(){
        ItemCreate itemCreate = ItemCreate.builder()
                .itemName("asd")
                .itemInformation("asd")
                .price("1000")
                .build();
        itemService.addItem(itemCreate);

        assertEquals(1L, repository.count());
        Item item = repository.findAll().get(0);
        assertEquals("asd", item.getItemName());
        assertEquals("asd", item.getItemInformation());
        assertEquals("1000", item.getPrice());
    }

    @Test
    @DisplayName("상품 수정")
    void test2(){
        Item item = Item.builder()
                .itemName("asd")
                .itemInformation("asd")
                .price("1000")
                .build();
        repository.save(item);

        ItemEdit itemEdit = ItemEdit.builder()
                .itemName("qwe")
                .itemInformation("qwe")
                .price("2000")
                .build();

        itemService.editItem(item.getId(), itemEdit);

        Item updateItem =repository.findById(item.getId()).orElseThrow(()-> new RuntimeException("없는 상품 id: " + item.getId()));
        assertEquals("qwe", updateItem.getItemName());
        assertEquals("qwe", updateItem.getItemInformation());
        assertEquals("2000", updateItem.getPrice());
    }

}