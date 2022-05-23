package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.entity.BasketItem;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final JpaBasketRepository basketRepository;
    private final JpaItemRepository itemRepository;
    private final JpaBasketItemRepository basketItemRepository;
    private final BasketItemRepository repository;
    private final JpaMemberRepository memberRepository;

    @Override
    @Transactional
    public void addBasket(Member member, Item addItem) {
        Member findMember = memberRepository.getById(member.getId());
        Basket basket = new Basket();
        basket.setMember(findMember);
        //Basket basket = basketRepository.findByMember(member.getId());

        if (basket == null){
            basket = Basket.addBasket(member);
            basketRepository.save(basket);
        }

        Item item = itemRepository.getById(addItem.getId());
        BasketItem basketItem = repository.findByItemAndBasket(item.getId(), basket.getId());

        if (basketItem == null){
            basketItem = BasketItem.addBasketItem(basket, item);
            basketItemRepository.save(basketItem);
        }
        else {
            BasketItem update = basketItem;
            update.setBasket(basketItem.getBasket());
            update.setItem(basketItem.getItem());
            basketItemRepository.save(update);
        }

    }
}
