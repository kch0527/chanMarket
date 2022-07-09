package com.example.market.service.comment;

import com.example.market.entity.Board;
import com.example.market.entity.Comment;
import com.example.market.repository.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    public void boardDeleteByComment(Long boardId){
        List<Comment> all = jpaCommentRepository.findAll();
        Iterator<Comment> iter = all.iterator();

        while (iter.hasNext()){
            Comment id1 = iter.next();
            if (boardId == id1.getBoard().getId()){
                jpaCommentRepository.delete(id1);
            }
        }
    }


    @Transactional
    public void deleteComment(Long commentId){
     jpaCommentRepository.deleteById(commentId);
    }



}
