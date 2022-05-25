package com.example.market.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class BasketItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    public static BasketItem addBasketItem(Basket basket, Item item){
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setItem(item);
        return basketItem;
    }

}
