package com.example.market.repository;

import com.example.market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {

}
