package com.example.market.repository;

import com.example.market.entity.basket.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaBasketItemRepository extends JpaRepository<BasketItem, Long> {

    @Query("select m from BasketItem m where m.basket = :basket and m.item = :item")
    BasketItem findBasketAndItem(@Param("basket") Long basket, @Param("item") Long item);
}




