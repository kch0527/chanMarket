package com.example.market.repository;

import com.example.market.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingAndCategoryContaining(String keyword, String category, Pageable pageable);

}
