package com.example.market.repository;

import com.example.market.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {

}
