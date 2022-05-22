package com.example.market.service;

import com.example.market.entity.Comment;
import com.example.market.entity.Item;
import com.example.market.entity.Member;
import com.example.market.repository.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        String format = nowTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        return format;
    }
    public void itemDeleteByComment(Item item){
        List<Comment> all = jpaCommentRepository.findAll();
        Iterator<Comment> iter = all.iterator();
        Long id = item.getId();

        while (iter.hasNext()){
            Comment id1 = iter.next();
            if (id == id1.getItem().getId()){
                jpaCommentRepository.delete(id1);
            }
        }
    }

    public Comment findComment(Long id){
        Comment findComment = jpaCommentRepository.getById(id);
        return findComment;
    }

    @Transactional
    public void editComment(Long commentId, Comment updateParam){
        Comment findComment = jpaCommentRepository.getById(commentId);
        findComment.setText(updateParam.getText());
        jpaCommentRepository.save(findComment);
    }

    @Transactional
    public void deleteComment(Long commentId){
     jpaCommentRepository.deleteById(commentId);
    }

}
