package com.example.market.service.basket;

import com.example.market.entity.basket.Basket;
import com.example.market.entity.basket.BasketItem;
import com.example.market.entity.board.Board;
import com.example.market.entity.member.Member;
import com.example.market.repository.BasketItemRepository;
import com.example.market.repository.JpaBasketItemRepository;
import com.example.market.repository.JpaBasketRepository;
import com.example.market.repository.JpaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final JpaBasketRepository basketRepository;
    private final JpaBasketItemRepository basketItemRepository;
    private final BasketItemRepository repository;
    private final JpaBoardRepository boardRepository;

    @Transactional
    public void addBasket(Member member){
        basketRepository.save(Basket.createBasket(member));
    }

    public List<BasketItem> BasketList(Long basketId){
        return findBasket(basketId).getBasketItemList();
    }

    public Basket findBasket(Long id){
        return basketRepository.getById(id);
    }


    @Override
    @Transactional
    public boolean addBasketItem(Member member, Board addBoard) {
        Basket basket = findBasket(member.getBasket().getId());

        Board board = boardRepository.getById(addBoard.getId());
        BasketItem basketItem = repository.findByItemAndBasket(board.getId(), basket.getId());

        if (!isExistBasketItem(basketItem)) {
            basketItem = BasketItem.addBasketItem(basket, board);
            basketItemRepository.save(basketItem);
            return true;
        }

        return false;

    }

    boolean isExistBasket(Basket basket) {
        return basket != null;
    }

    boolean isExistBasketItem(BasketItem basketItem) {
        return basketItem != null;
    }


    @Transactional
    public void deleteBasketItem(Long basketItemId){
       basketItemRepository.deleteById(basketItemId);
    }

}
