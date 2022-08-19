package com.example.market.repository;

import com.example.market.entity.basket.BasketItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class BasketItemRepository {
    @PersistenceContext //스프링이 엔티티매니저 만들어서 주입
    private EntityManager entityManager;

    public BasketItem findByItemAndBasket(Long boardId, Long basketId) {
        try {
            return entityManager.createQuery("select m from BasketItem m where m.board.id = :boardId and m.basket.id =:basketId", BasketItem.class)
                    .setParameter("boardId", boardId)
                    .setParameter("basketId", basketId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
