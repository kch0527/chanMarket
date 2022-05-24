package com.example.market.service;

import com.example.market.entity.Item;
import com.example.market.entity.Member;

public interface BasketService {
    public boolean addBasketItem(Member member, Item item);
}
