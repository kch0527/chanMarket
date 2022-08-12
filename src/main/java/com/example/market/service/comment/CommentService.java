package com.example.market.service.comment;

import com.example.market.entity.comment.Comment;
import com.example.market.entity.member.Member;
import com.example.market.request.comment.CommentCreate;
import com.example.market.request.comment.CommentEdit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Comment addComment(CommentCreate commentCreate, Long boardId, Member member);
    Page<Comment> commentList(Long boardId, Pageable pageable);
    String nowTime();
    Comment findComment(Long id);
    void editComment(Long commentId, CommentEdit updateParam);
    void deleteComment(Long commentId);
}
