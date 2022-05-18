package com.example.market.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class BasketItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BASKET_ID")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public static BasketItem addBasketItem(Basket basket, Item item){
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setItem(item);
        return basketItem;
    }

}
