package com.example.market.entity.item;

import com.example.market.entity.BasketItem;
import com.example.market.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Lob
    private String itemInformation;

    private String itemName;

    private String price;

    @Builder
    public Item(Board board, String itemInformation, String itemName, String price, List<BasketItem> basketItemList) {
        this.board = board;
        this.itemInformation = itemInformation;
        this.itemName = itemName;
        this.price = price;
        this.basketItemList = basketItemList;
    }

    public ItemEditor.ItemEditorBuilder toEditor(){
        return ItemEditor.builder()
                .itemInformation(itemInformation)
                .itemName(itemName)
                .price(price);
    }

    public void edit(ItemEditor itemEditor){
        itemName = itemEditor.getItemName();
        itemInformation = itemEditor.getItemInformation();
        price = itemEditor.getPrice();
    }

    @OneToMany(mappedBy = "item", cascade=CascadeType.REMOVE)
    private List<BasketItem> basketItemList = new ArrayList<>();

}
