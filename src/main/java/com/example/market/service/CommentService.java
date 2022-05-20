package com.example.market.service;

import com.example.market.entity.Comment;
import com.example.market.repository.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JpaCommentRepository jpaCommentRepository;

    @Transactional
    public Comment addComment(Comment comment){
        jpaCommentRepository.save(comment);
        return comment;
    }

    public String nowTime(){
        LocalDateTime nowTime = LocalDateTime.now();
        String format = nowTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        return format;
    }
}
