package com.example.market.repository;

import com.example.market.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {

}
