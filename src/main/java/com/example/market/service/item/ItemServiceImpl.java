package com.example.market.service.item;

import com.example.market.entity.item.Item;
import com.example.market.entity.item.ItemEditor;
import com.example.market.exception.ItemNotFound;
import com.example.market.repository.JpaItemRepository;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.item.ItemEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final JpaItemRepository jpaItemRepository;

    @Transactional
    public Item addItem(ItemCreate itemCreate){
        Item item = Item.builder()
                .itemName(itemCreate.getItemName())
                .itemInformation(itemCreate.getItemInformation())
                .price(itemCreate.getPrice())
                .board(itemCreate.getBoard())
                .build();
        jpaItemRepository.save(item);
        return item;
    }


    @Transactional
    public void editItem(Long itemId, ItemEdit updateParam){
        Item item = jpaItemRepository.findById(itemId).orElseThrow(ItemNotFound::new);

        ItemEditor.ItemEditorBuilder itemEditorBuilder =item.toEditor();

        ItemEditor itemEditor = itemEditorBuilder.itemName(updateParam.getItemName())
                .itemInformation(updateParam.getItemInformation())
                .price(updateParam.getPrice())
                .build();
        item.edit(itemEditor);

    }

    @Transactional
    public Item readItem(Long itemId){
        return jpaItemRepository.getById(itemId);
    }

    public List<Item> itemList(){
        return jpaItemRepository.findAll();
    }




}
