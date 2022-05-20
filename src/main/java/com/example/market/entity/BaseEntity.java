package com.example.market.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    private String uploadBy;
    private LocalDateTime uploadDate;
    private String updateBy;
    private LocalDateTime updateDate;

/*
    @PrePersist
    protected void onPersist(){  //모든 엔티티의 생성시간 수정시간 기록
        this.uploadDate = this.updateDate = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updateDate = LocalDateTime.now();
    }
 */

}
