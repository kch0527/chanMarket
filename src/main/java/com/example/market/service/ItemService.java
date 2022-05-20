package com.example.market.service;

import com.example.market.entity.Item;
import com.example.market.repository.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final JpaItemRepository jpaItemRepository;

    @Transactional
    public Item addItem(Item item){
        jpaItemRepository.save(item);
        return item;
    }

    @Transactional
    public void deleteItem(Long itemId){
        jpaItemRepository.deleteById(itemId);
    }

    @Transactional
    public void editItem(Long itemId, Item updateParam){
        Item findItem = jpaItemRepository.getById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setItemInformation(updateParam.getItemInformation());
        jpaItemRepository.save(findItem);
    }

    @Transactional
    public Item readItem(Long itemId){
        return jpaItemRepository.getById(itemId);
    }

    public List<Item> itemList(){
        List<Item> itemList = jpaItemRepository.findAll();
        return itemList;
    }


}
