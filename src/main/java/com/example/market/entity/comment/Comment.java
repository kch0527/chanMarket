package com.example.market.entity.comment;

import com.example.market.entity.Board;
import com.example.market.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "comment_id", insertable = false, updatable = false)
    //private Comment highLevelId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Lob
    private String text;

    private String nowTime;

    public CommentEditor.CommentEditorBuilder commentEditorBuilder(){
        return CommentEditor.builder()
                .nowTime(nowTime)
                .text(text);
    }

    public void edit(CommentEditor commentEditor){
        text = commentEditor.getText();
        nowTime = commentEditor.getNowTime();
    }


}
