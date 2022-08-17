package com.example.market.entity.basket;

import com.example.market.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class BasketItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static BasketItem addBasketItem(Basket basket, Board board){
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setBoard(board);
        return basketItem;
    }

}
