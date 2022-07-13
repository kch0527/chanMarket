package com.example.market.service.basket;

import com.example.market.entity.basket.Basket;
import com.example.market.entity.basket.BasketItem;
import com.example.market.entity.item.Item;
import com.example.market.entity.member.Member;

import java.util.List;

public interface BasketService {
    public boolean addBasketItem(Member member, Item item);
    void addBasket(Member member);
    Basket findBasket(Long id);

    List<BasketItem> BasketList(Long basketId);

    void deleteBasketItem(Long basketItemId);

}
