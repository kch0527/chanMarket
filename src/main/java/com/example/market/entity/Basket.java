package com.example.market.entity;

import com.example.market.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Basket {
    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "basket", cascade=CascadeType.REMOVE)
    private List<BasketItem> basketItemList = new ArrayList<>();

    public static Basket createBasket(Member member){
        Basket basket = new Basket();
        basket.setMember(member);
        return basket;
    }

}
