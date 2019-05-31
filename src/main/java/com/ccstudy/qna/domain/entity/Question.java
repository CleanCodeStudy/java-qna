package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {

    private String id;
    private String title;
    private String contents;
    private String userId;
    private LocalDate createDate;
    private LocalDate modifyDate;

    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String contents, String userId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.createDate = LocalDate.now();
    }
}
