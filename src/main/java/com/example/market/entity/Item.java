package com.example.market.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorColumn
public class Item{
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @NotNull
    private String itemName;

    @NotNull
    private String price;

    @NotNull
    @Size(max = 255)
    private String itemInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "item")
    private List<Comment> comments = new ArrayList<>();

}
