package com.example.market.repository;

import com.example.market.entity.basket.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaBasketRepository extends JpaRepository<Basket, Long> {


}
