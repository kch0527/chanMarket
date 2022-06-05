package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.entity.BasketItem;
import com.example.market.entity.Item;
import com.example.market.entity.Member;

import java.util.List;

public interface BasketService {
    public boolean addBasketItem(Member member, Item item);
    void addBasket(Member member);
    Basket findBasket(Long id);

    List<BasketItem> BasketList(Long basketId);

    void deleteBasketItem(Long basketItemId);

}
