package com.example.market.service;

import com.example.market.entity.*;
import com.example.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final JpaBasketRepository basketRepository;
    private final JpaItemRepository itemRepository;
    private final JpaBasketItemRepository basketItemRepository;
    private final BasketItemRepository repository;


    private final MemberService memberService;

    @Transactional
    public void addBasket(Member member){
        Basket basket = memberService.findBasket(member.getId());
        if (!isExistBasket(basket)) {
            basket = Basket.createBasket(member);
            basketRepository.save(basket);
        }
    }

    public List<BasketItem> BasketList(Long basketId){
        return findBasket(basketId).getBasketItemList();
    }

    public Basket findBasket(Long id){
        return basketRepository.getById(id);
    }


    @Override
    @Transactional
    public boolean addBasketItem(Member member, Item addItem) {
        Basket basket = memberService.findBasket(member.getId());

        Item item = itemRepository.getById(addItem.getId());
        BasketItem basketItem = repository.findByItemAndBasket(item.getId(), basket.getId());

        if (!isExistBasketItem(basketItem)) {
            basketItem = BasketItem.addBasketItem(basket, item);
            basketItemRepository.save(basketItem);
            return true;
        }

        return false;

    }

    boolean isExistBasket(Basket basket) {
        return basket != null;
    }

    boolean isExistBasketItem(BasketItem basketItem) {
        return basketItem != null;
    }


    @Transactional
    public void deleteBasketItem(Long basketItemId){
       basketItemRepository.deleteById(basketItemId);
    }

}
