package com.example.market.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Basket {
    @Id
    @GeneratedValue
    @Column(name = "BASKET_ID")
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    @OneToMany(mappedBy = "basket", cascade=CascadeType.ALL)
    private List<BasketItem> basketItemList = new ArrayList<>();

    public static Basket addBasket(Member member){
        Basket basket = new Basket();
        basket.setMember(member);
        return basket;
    }

}
