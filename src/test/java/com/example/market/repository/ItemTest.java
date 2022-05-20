package com.example.market.repository;

import com.example.market.service.ItemService;
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
    public ItemTest(ItemService service){
        this.service = service;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testItem() throws Exception{

        service.deleteItem(49L);
        service.deleteItem(50L);
        service.deleteItem(51L);
        service.deleteItem(52L);
        service.deleteItem(53L);
        service.deleteItem(54L);
        service.deleteItem(55L);
    }

    @Test
    public void test2(){
        ItemService itemService1 = service;
        ItemService itemService2 = service;
        System.out.println("itemService1 = " + itemService1);
        System.out.println("itemService2 = " + itemService2);
    }



}
