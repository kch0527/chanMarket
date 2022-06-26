package com.example.market.repository;

import com.example.market.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m where m.chatRoom.id = :roomId")
    List<Message> findRoomMessage(@Param("roomId") Long roomId);
}
