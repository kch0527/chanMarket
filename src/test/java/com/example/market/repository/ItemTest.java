package com.example.market.repository;

import com.example.market.service.ItemService;
import com.example.market.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest
class ItemTest {

    private final ItemService service;

    @Autowired
    public ItemTest(ItemServiceImpl service){
        this.service = service;
    }

    @Test
    @Transactional
    public void itemDelete(){
        service.deleteItem(4L);
    }

    @Test
    public void test2(){

    }



}
