package com.example.market.service.comment;


import com.example.market.entity.comment.Comment;
import com.example.market.entity.comment.CommentEditor;
import com.example.market.entity.member.Member;
import com.example.market.exception.CommentNotFound;
import com.example.market.repository.JpaCommentRepository;
import com.example.market.request.comment.CommentCreate;
import com.example.market.request.comment.CommentEdit;
import com.example.market.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final JpaCommentRepository jpaCommentRepository;
    private final BoardService boardService;

    @Transactional
    public Comment addComment(CommentCreate commentCreate, Long boardId, Member member){
        Comment comment = Comment.builder()
                .board(boardService.findBoard(boardId))
                .member(member)
                .nowTime(nowTime())
                .text(commentCreate.getText())
                .build();
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
    public void editComment(Long commentId, CommentEdit updateParam){
        Comment findComment = jpaCommentRepository.getById(commentId);
        CommentEditor.CommentEditorBuilder commentEditorBuilder = findComment.commentEditorBuilder();
        CommentEditor commentEditor = commentEditorBuilder
                .text(updateParam.getText())
                .nowTime(nowTime())
                .build();
        findComment.edit(commentEditor);
    }


    @Transactional
    public void deleteComment(Long commentId){
     jpaCommentRepository.delete(jpaCommentRepository.findById(commentId).orElseThrow(CommentNotFound::new));
    }



}
