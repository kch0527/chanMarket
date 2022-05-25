package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Size(max = 255)
    private String itemInformation;

    @NotNull
    private String itemName;

    @NotNull
    private String price;

}
