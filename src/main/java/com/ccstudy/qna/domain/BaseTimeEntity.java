package com.ccstudy.qna.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class BaseTimeEntity {

    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    public BaseTimeEntity() {
        this.registerDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
}
