package com.example.market.service;

import com.example.market.entity.Item;
import com.example.market.entity.Member;

public interface BasketService {
    public void addBasket(Member member, Item item);
}
