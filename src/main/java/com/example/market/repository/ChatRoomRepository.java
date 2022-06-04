package com.example.market.repository;

import com.example.market.entity.BasketItem;
import com.example.market.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select m from ChatRoom m where m.member.id = :memberId or m.owner.id = :memberId")
    List<ChatRoom> findMyChatRooms(@Param("memberId") Long memberId);
}
