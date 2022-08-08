package com.example.market.repository;

import com.example.market.entity.basket.Basket;
import com.example.market.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

    @Query("select m from Comment m where m.board.id = :board")
    Page<Comment> findByComment(@Param("board") Long boardId, Pageable pageable);
}
