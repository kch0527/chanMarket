package com.example.market.service;

import com.example.market.model.Basket;
import com.example.market.model.BasketItem;
import com.example.market.model.Item;
import com.example.market.model.Member;
import com.example.market.repository.JpaBasketItemRepository;
import com.example.market.repository.JpaBasketRepository;
import com.example.market.repository.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final JpaBasketRepository basketRepository;
    private final JpaItemRepository itemRepository;
    private final JpaBasketItemRepository basketItemRepository;

    @Override
    @Transactional
    public void addBasket(Member member, Item addItem) {
        Basket basket = basketRepository.findByMember(member.getId()); //2022.05.18 멤버아이디로 바스킷 찾기

        if (basket == null){
            basket = Basket.addBasket(member);
            basketRepository.save(basket);
        }

        Item item = itemRepository.getById(addItem.getId());
        BasketItem basketItem = basketItemRepository.findBasketAndItem(basket.getId(), item.getId());

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
