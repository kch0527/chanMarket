package com.example.market.repository;

import com.example.market.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {

}
