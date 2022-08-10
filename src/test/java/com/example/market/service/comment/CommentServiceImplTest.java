package com.example.market.service.comment;

import com.example.market.MarketApplication;
import com.example.market.entity.Board;
import com.example.market.entity.comment.Comment;
import com.example.market.entity.item.Item;
import com.example.market.entity.member.Member;
import com.example.market.repository.JpaCommentRepository;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.repository.MemberRepository;
import com.example.market.request.comment.CommentCreate;
import com.example.market.request.item.ItemCreate;
import com.example.market.request.member.MemberCreate;
import com.example.market.service.board.BoardServiceImpl;
import com.example.market.service.item.ItemServiceImpl;
import com.example.market.service.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = MarketApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private JpaCommentRepository repository;
    @Autowired
    private JpaMemberRepository memberRepository;

    @Autowired
    private BoardServiceImpl boardService;

    @BeforeEach
    void clear(){
        repository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("댓글 쓰기")
    void test1(){
        Member member = Member.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberRepository.save(member);

        Board board = boardService.createBoard(new Board(), member);

        CommentCreate commentCreate = CommentCreate.builder()
                .text("hahaha")
                .build();

        commentService.addComment(commentCreate, board.getId(), member);

        assertEquals(1L, repository.count());
        Comment comment = repository.findAll().get(0);
        assertEquals("hahaha", comment.getText());
        assertEquals(board, comment.getBoard());
        assertEquals(member, comment.getMember());
        assertEquals(commentService.nowTime() ,comment.getNowTime());
    }

    @Test
    @DisplayName("댓글 삭제")
    void test2(){
        Member member = Member.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberRepository.save(member);

        Board board = boardService.createBoard(new Board(), member);

        CommentCreate commentCreate = CommentCreate.builder()
                .text("hahaha")
                .build();

        Comment comment = commentService.addComment(commentCreate, board.getId(), member);

        commentService.deleteComment(comment.getId());
        assertEquals(0L, repository.count());
    }

    @Test
    @DisplayName("댓글 리스트 조회")
    void test3(){
        Member member = Member.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberRepository.save(member);

        Member member1 = Member.builder()
                .email("jhgu12@google.com")
                .name("찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberRepository.save(member1);

        Board board = boardService.createBoard(new Board(), member);

        CommentCreate commentCreate = CommentCreate.builder()
                .text("hahaha")
                .build();
        commentService.addComment(commentCreate, board.getId(), member);

        CommentCreate commentCreate1 = CommentCreate.builder()
                .text("zzzzzz")
                .build();
        commentService.addComment(commentCreate1, board.getId(), member1);

        assertEquals(2L, repository.count());

        Comment findComment = repository.findAll().get(0);
        assertEquals("hahaha", findComment.getText());
        assertEquals(board, findComment.getBoard());
        assertEquals(member, findComment.getMember());
        assertEquals(commentService.nowTime() ,findComment.getNowTime());

        Comment findComment1 = repository.findAll().get(1);
        assertEquals("zzzzzz", findComment1.getText());
        assertEquals(board, findComment1.getBoard());
        assertEquals(member1, findComment1.getMember());
        assertEquals(commentService.nowTime() ,findComment1.getNowTime());
    }

    @Test
    @DisplayName("게시글 마다 댓글 조회")
    void test4(){
        Member member = Member.builder()
                .email("jhgu12@naver.com")
                .name("김찬회")
                .pw("123412341")
                .tel("010-1111-1111")
                .build();
        memberRepository.save(member);

        Board board1 = boardService.createBoard(new Board(), member);
        Board board2 = boardService.createBoard(new Board(), member);

        CommentCreate commentCreate = CommentCreate.builder()
                .text("hahaha")
                .build();
        commentService.addComment(commentCreate, board1.getId(), member);

        CommentCreate commentCreate1 = CommentCreate.builder()
                .text("zzzzzz")
                .build();
        commentService.addComment(commentCreate1, board2.getId(), member);

        assertEquals(2L, repository.count());

        Comment findComment = repository.findAll().get(0);
        assertEquals("hahaha", findComment.getText());
        assertEquals(board1, findComment.getBoard());
        assertEquals(member, findComment.getMember());
        assertEquals(commentService.nowTime() ,findComment.getNowTime());

        Comment findComment1 = repository.findAll().get(1);
        assertEquals("zzzzzz", findComment1.getText());
        assertEquals(board2, findComment1.getBoard());
        assertEquals(member, findComment1.getMember());
        assertEquals(commentService.nowTime() ,findComment1.getNowTime());
    }
}
