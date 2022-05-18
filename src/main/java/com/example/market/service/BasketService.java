package com.example.market.service;

import com.example.market.model.Item;
import com.example.market.model.Member;

public interface BasketService {
    public void addBasket(Member member, Item item);
}
