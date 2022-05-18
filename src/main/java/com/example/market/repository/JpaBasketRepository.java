package com.example.market.repository;

import com.example.market.model.Basket;
import com.example.market.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaBasketRepository extends JpaRepository<Basket, Long> {

    @Query("select m from Basket m where m.member.id = :member")
    Basket findByMember(@Param("member") Long memberId);

}
