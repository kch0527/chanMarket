package com.example.market.repository;

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

    private final ItemServiceImpl service;

    @Autowired
    public ItemTest(ItemServiceImpl service){
        this.service = service;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testItem() throws Exception{

    }

    @Test
    public void test2(){
        ItemServiceImpl itemService1 = service;
        ItemServiceImpl itemService2 = service;
        System.out.println("itemService1 = " + itemService1);
        System.out.println("itemService2 = " + itemService2);
    }



}
