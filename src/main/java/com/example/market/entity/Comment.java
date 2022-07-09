package com.example.market.entity;

import com.example.market.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Comment highLevelId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Size(max = 100)
    @NotNull
    private String text;

    private String nowTime;


}
